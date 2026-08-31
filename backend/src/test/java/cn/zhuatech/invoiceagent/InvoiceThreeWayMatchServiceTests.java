/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent;

import cn.zhuatech.invoiceagent.service.InvoiceThreeWayMatchService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class InvoiceThreeWayMatchServiceTests {
    private final InvoiceThreeWayMatchService service = new InvoiceThreeWayMatchService();

    @Test void holdsAmountOutsideTolerance() {
        var result = service.match(new InvoiceThreeWayMatchService.Request("INV-9001",
                new BigDecimal("120000"), new BigDecimal("120000"), new BigDecimal("100000"),
                new BigDecimal("2"), false, true, true));
        assertThat(result.route()).isEqualTo("THREE_WAY_EXCEPTION");
        assertThat(result.holdAmount()).isEqualByComparingTo("20000.00");
        assertThat(result.postingAllowed()).isFalse();
    }

    @Test void postsApprovedMatchedInvoice() {
        var result = service.match(new InvoiceThreeWayMatchService.Request("INV-9002",
                new BigDecimal("100000"), new BigDecimal("100000"), new BigDecimal("99500"),
                new BigDecimal("1"), false, true, true));
        assertThat(result.route()).isEqualTo("POST_APPROVED");
        assertThat(result.postingAllowed()).isTrue();
    }
}
