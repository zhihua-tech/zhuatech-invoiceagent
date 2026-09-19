/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent.controller;import cn.zhuatech.invoiceagent.common.ApiResponse;import cn.zhuatech.invoiceagent.service.InvoiceDuplicateRiskService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController@RequestMapping("/api/enterprise/invoice")public class InvoiceDuplicateRiskController{private final InvoiceDuplicateRiskService service;/**
                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                        */
public InvoiceDuplicateRiskController(InvoiceDuplicateRiskService s){service=s;}/**
                                                                                                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                        */
@PostMapping("/duplicate-risk")public ApiResponse<InvoiceDuplicateRiskService.Result>assess(@Valid@RequestBody InvoiceDuplicateRiskService.Request r){return ApiResponse.ok("发票重复风险评估完成",service.assess(r));}}
