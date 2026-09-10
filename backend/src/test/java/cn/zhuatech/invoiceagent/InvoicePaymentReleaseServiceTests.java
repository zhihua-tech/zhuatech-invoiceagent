/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent;

import cn.zhuatech.invoiceagent.service.InvoicePaymentReleaseService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class InvoicePaymentReleaseServiceTests {
    private final InvoicePaymentReleaseService service = new InvoicePaymentReleaseService();

    @Test
    void releasesFullyControlledPayment() {
        var result = service.evaluate(request(Set.of("approver-a", "approver-b"), 2,
                false, true, false, true, true));
        assertThat(result.decision()).isEqualTo(InvoicePaymentReleaseService.Decision.RELEASE);
        assertThat(result.paymentFingerprint()).hasSize(64);
    }

    @Test
    void requestsMissingIndependentApproval() {
        var result = service.evaluate(request(Set.of("approver-a"), 2,
                false, true, false, true, true));
        assertThat(result.decision()).isEqualTo(InvoicePaymentReleaseService.Decision.SECOND_APPROVAL);
        assertThat(result.route()).isEqualTo("approval-queue");
    }

    @Test
    void holdsDisputedOrUnbudgetedPayment() {
        var result = service.evaluate(request(Set.of("approver-a", "approver-b"), 2,
                false, true, true, false, true));
        assertThat(result.decision()).isEqualTo(InvoicePaymentReleaseService.Decision.HOLD);
        assertThat(result.actions()).hasSize(2);
    }

    @Test
    void blocksUnverifiedBankChangeAndRequesterApproval() {
        var result = service.evaluate(request(Set.of("requester-a", "approver-b"), 2,
                true, false, false, true, true));
        assertThat(result.decision()).isEqualTo(InvoicePaymentReleaseService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(2);
    }

    private InvoicePaymentReleaseService.ReleaseRequest request(
            Set<String> approvers, int requiredApprovals, boolean bankChanged,
            boolean bankVerified, boolean hold, boolean budget, boolean sanctionsPassed) {
        return new InvoicePaymentReleaseService.ReleaseRequest("INV-100", "V-100",
                new BigDecimal("26800.00"), LocalDate.of(2026, 9, 20), LocalDate.of(2026, 9, 11),
                true, true, false, true, true, sanctionsPassed, bankChanged, bankVerified,
                hold, budget, "requester-a", approvers, requiredApprovals);
    }
}
