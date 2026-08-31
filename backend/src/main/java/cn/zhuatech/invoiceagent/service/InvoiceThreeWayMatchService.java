/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/** 企业发票三单匹配，异常金额进入挂账而不是直接入账。 */
@Service
public class InvoiceThreeWayMatchService {
    public Decision match(Request request) {
        BigDecimal matchedAmount = request.invoiceAmount().min(request.purchaseOrderAmount())
                .min(request.goodsReceiptAmount()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal holdAmount = request.invoiceAmount().subtract(matchedAmount)
                .max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
        BigDecimal variancePercent = holdAmount.multiply(BigDecimal.valueOf(100))
                .divide(request.invoiceAmount(), 2, RoundingMode.HALF_UP);
        boolean withinTolerance = variancePercent.compareTo(request.tolerancePercent()) <= 0;
        int riskScore = (request.duplicateRisk() ? 60 : 0)
                + (!request.taxRuleMatched() ? 25 : 0)
                + (!withinTolerance ? 25 : 0);
        String route = request.duplicateRisk() ? "BLOCK_DUPLICATE"
                : !request.taxRuleMatched() ? "TAX_REVIEW"
                : !withinTolerance ? "THREE_WAY_EXCEPTION"
                : request.financeApproved() ? "POST_APPROVED" : "FINANCE_APPROVAL";
        boolean postingAllowed = !request.duplicateRisk() && request.taxRuleMatched()
                && withinTolerance && request.financeApproved();
        List<String> controls = new ArrayList<>();
        controls.add("固化采购订单、收货单、发票及供应商主数据快照");
        if (!withinTolerance) controls.add("超容差金额 " + holdAmount + " 进入异常挂账队列");
        if (request.duplicateRisk()) controls.add("按发票代码、号码、销方税号和金额阻断重复入账");
        if (!request.taxRuleMatched()) controls.add("由税务专员复核税率和进项抵扣资格");
        return new Decision(request.invoiceCode(), matchedAmount, holdAmount, variancePercent,
                Math.min(100, riskScore), route, postingAllowed, List.copyOf(controls));
    }

    public record Request(@NotBlank String invoiceCode,
                          @DecimalMin("0.01") BigDecimal invoiceAmount,
                          @DecimalMin("0") BigDecimal purchaseOrderAmount,
                          @DecimalMin("0") BigDecimal goodsReceiptAmount,
                          @DecimalMin("0") @DecimalMax("20") BigDecimal tolerancePercent,
                          boolean duplicateRisk, boolean taxRuleMatched, boolean financeApproved) {}

    public record Decision(String invoiceCode, BigDecimal matchedAmount, BigDecimal holdAmount,
                           BigDecimal variancePercent, int riskScore, String route,
                           boolean postingAllowed, List<String> controls) {}
}
