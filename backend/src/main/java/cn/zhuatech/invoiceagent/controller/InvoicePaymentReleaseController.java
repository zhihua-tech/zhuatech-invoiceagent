/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent.controller;

import cn.zhuatech.invoiceagent.common.ApiResponse;
import cn.zhuatech.invoiceagent.service.InvoicePaymentReleaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/invoice")
public class InvoicePaymentReleaseController {
    private final InvoicePaymentReleaseService service;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public InvoicePaymentReleaseController(InvoicePaymentReleaseService service) {
        this.service = service;
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/payment-release")
    public ApiResponse<InvoicePaymentReleaseService.ReleaseResult> evaluate(
            @Valid @RequestBody InvoicePaymentReleaseService.ReleaseRequest request) {
        return ApiResponse.ok("发票付款放行评估完成", service.evaluate(request));
    }
}
