/* Copyright 2026 上海如静知华信息科技有限公司 */
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
@RestController @RequestMapping("/api/shopfloor") @PreAuthorize("hasAnyRole('DOMAIN_USER','ADMIN')")
public class WorkspaceController {
 private final InvoiceAgentService service; private final AgentRuntime runtime; private final InvoiceAuditService domainAgent;
 public WorkspaceController(InvoiceAgentService service,AgentRuntime runtime,InvoiceAuditService domainAgent){this.service=service;this.runtime=runtime;this.domainAgent=domainAgent;}
 @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.shopfloorDashboard());}
 @PostMapping("/work-orders/{id}/reports") public ApiResponse<ReportResult> report(@PathVariable Long id,@Valid @RequestBody ReportRequest request){return ApiResponse.ok("反馈提交成功",service.report(id,request));}
 @PostMapping("/agent-preview") public ApiResponse<AgentRuntime.AgentResult> preview(@RequestBody Map<String,String> body){return ApiResponse.ok(runtime.run(new AgentRuntime.AgentRequest(body.getOrDefault("objective","核验发票并关联合同与收货记录"),Map.of("mode","demo","approval","required"))));}
 @PostMapping("/invoice-audit") public ApiResponse<InvoiceAuditService.AuditDecision> domainAction(@Valid @RequestBody InvoiceAuditService.AuditRequest request){return ApiResponse.ok("发票审核规则检查完成",domainAgent.inspect(request));}
}
