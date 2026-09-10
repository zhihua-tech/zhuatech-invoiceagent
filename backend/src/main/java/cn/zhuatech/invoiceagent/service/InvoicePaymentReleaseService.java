/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.Set;

/** 在付款批次生成前执行发票、供应商、账户、预算、审批和职责分离门禁。 */
@Service
public class InvoicePaymentReleaseService {
    public ReleaseResult evaluate(ReleaseRequest request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        long daysToDue = ChronoUnit.DAYS.between(request.asOfDate(), request.dueDate());

        if (!request.invoiceAuthentic()) blockers.add("发票真实性校验未通过");
        if (!request.threeWayMatchPassed()) blockers.add("采购订单、收货和发票三单匹配未通过");
        if (request.duplicateRisk()) blockers.add("检测到重复付款风险");
        if (!request.vendorActive()) blockers.add("供应商已停用或冻结");
        if (!request.taxRuleMatched()) blockers.add("税务规则校验未通过");
        if (!request.sanctionsScreenPassed()) blockers.add("供应商制裁筛查未通过");
        if (request.bankAccountChanged() && !request.bankChangeVerified()) {
            blockers.add("供应商收款账户变更未独立回拨验证");
        }
        if (request.approverIds().contains(request.requesterId())) {
            blockers.add("付款申请人与审批人未实现职责分离");
        }

        String fingerprint = fingerprint(request);
        if (!blockers.isEmpty()) {
            actions.add("阻断付款批次并保全发票、供应商及审批证据");
            return result(Decision.BLOCKED, "risk-control", daysToDue, fingerprint, blockers, actions);
        }

        if (request.disputeOrHoldActive() || !request.budgetAvailable()) {
            if (request.disputeOrHoldActive()) actions.add("保留付款冻结并完成争议或止付事项处理");
            if (!request.budgetAvailable()) actions.add("补充预算或资金计划后重新排款");
            return result(Decision.HOLD, "payment-hold", daysToDue, fingerprint, blockers, actions);
        }

        if (request.approverIds().size() < request.requiredApprovals()) {
            actions.add("补齐 " + (request.requiredApprovals() - request.approverIds().size()) + " 名独立审批人");
            return result(Decision.SECOND_APPROVAL, "approval-queue", daysToDue, fingerprint, blockers, actions);
        }

        actions.add("生成付款指令并归档三单匹配、账户验证、预算和审批证据");
        return result(Decision.RELEASE, "payment-run", daysToDue, fingerprint, blockers, actions);
    }

    private ReleaseResult result(Decision decision, String route, long daysToDue, String fingerprint,
                                 List<String> blockers, List<String> actions) {
        return new ReleaseResult(decision, route, daysToDue, fingerprint,
                List.copyOf(blockers), List.copyOf(actions));
    }

    private String fingerprint(ReleaseRequest request) {
        String source = request.invoiceCode() + "|" + request.vendorId() + "|"
                + request.amount().stripTrailingZeros().toPlainString();
        try {
            return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256")
                    .digest(source.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 unavailable", exception);
        }
    }

    public record ReleaseRequest(
            @NotBlank String invoiceCode,
            @NotBlank String vendorId,
            @NotNull @DecimalMin("0.01") BigDecimal amount,
            @NotNull LocalDate dueDate,
            @NotNull LocalDate asOfDate,
            boolean invoiceAuthentic,
            boolean threeWayMatchPassed,
            boolean duplicateRisk,
            boolean vendorActive,
            boolean taxRuleMatched,
            boolean sanctionsScreenPassed,
            boolean bankAccountChanged,
            boolean bankChangeVerified,
            boolean disputeOrHoldActive,
            boolean budgetAvailable,
            @NotBlank String requesterId,
            @NotEmpty Set<@NotBlank String> approverIds,
            @Min(1) int requiredApprovals
    ) {}

    public record ReleaseResult(Decision decision, String route, long daysToDue,
                                String paymentFingerprint, List<String> blockers,
                                List<String> actions) {}

    public enum Decision { RELEASE, SECOND_APPROVAL, HOLD, BLOCKED }
}
