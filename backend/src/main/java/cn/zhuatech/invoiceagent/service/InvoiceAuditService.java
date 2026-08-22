/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/** 发票审核规则：重复票、合同不匹配和税务异常均不能自动入账。 */
@Service
public class InvoiceAuditService {
    public record AuditRequest(
            @NotBlank String invoiceCode,
            @DecimalMin("0.01") BigDecimal amount,
            boolean contractMatched,
            boolean duplicateRisk,
            boolean taxRuleMatched,
            boolean financeApproved) {}

    public record AuditDecision(
            boolean postingAllowed,
            String route,
            int riskScore,
            List<String> checks) {}

    public AuditDecision inspect(AuditRequest request) {
        int risk = (request.duplicateRisk() ? 55 : 0)
                + (!request.contractMatched() ? 25 : 0)
                + (!request.taxRuleMatched() ? 20 : 0);
        boolean humanReview = risk > 0;
        boolean allowed = !humanReview || request.financeApproved();
        String route = request.duplicateRisk() ? "BLOCK_DUPLICATE"
                : !request.contractMatched() ? "CONTRACT_REVIEW"
                : !request.taxRuleMatched() ? "TAX_REVIEW" : "CHECK_PASSED";
        return new AuditDecision(allowed, route, Math.min(100, risk), List.of(
                "核对发票代码、号码、金额和销方税号",
                "关联采购订单、合同、收货和付款申请",
                "异常票据由财务专员确认后方可入账"));
    }
}
