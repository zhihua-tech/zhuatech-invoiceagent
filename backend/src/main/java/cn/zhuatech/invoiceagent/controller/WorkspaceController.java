/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent.controller;
import cn.zhuatech.invoiceagent.agent.AgentRuntime;
import cn.zhuatech.invoiceagent.common.ApiResponse;
import cn.zhuatech.invoiceagent.dto.InvoiceAgentDto.*;
import cn.zhuatech.invoiceagent.service.InvoiceAgentService;
import cn.zhuatech.invoiceagent.service.InvoiceAuditService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/shopfloor") @PreAuthorize("hasAnyRole('DOMAIN_USER','ADMIN')")
public class WorkspaceController {
 private final InvoiceAgentService service; private final AgentRuntime runtime; private final InvoiceAuditService domainAgent;
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public WorkspaceController(InvoiceAgentService service,AgentRuntime runtime,InvoiceAuditService domainAgent){this.service=service;this.runtime=runtime;this.domainAgent=domainAgent;}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.shopfloorDashboard());}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/work-orders/{id}/reports") public ApiResponse<ReportResult> report(@PathVariable Long id,@Valid @RequestBody ReportRequest request){return ApiResponse.ok("反馈提交成功",service.report(id,request));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/agent-preview") public ApiResponse<AgentRuntime.AgentResult> preview(@RequestBody Map<String,String> body){return ApiResponse.ok(runtime.run(new AgentRuntime.AgentRequest(body.getOrDefault("objective","核验发票并关联合同与收货记录"),Map.of("mode","demo","approval","required"))));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/invoice-audit") public ApiResponse<InvoiceAuditService.AuditDecision> domainAction(@Valid @RequestBody InvoiceAuditService.AuditRequest request){return ApiResponse.ok("发票审核规则检查完成",domainAgent.inspect(request));}
}
