/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent.agent;
import org.springframework.stereotype.Component; import java.util.List; import java.util.Map;
/**
 * 企业发票审核智能体平台运行边界；默认演示执行器不连接真实模型、业务系统或外部通信渠道。
 *
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
public interface AgentRuntime {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 AgentResult run(AgentRequest request);
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 record AgentRequest(String objective,Map<String,String> context){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 record AgentStep(String name,String status,String evidence){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 record AgentResult(String runtime,String summary,List<AgentStep> steps,Map<String,Object> metrics){}
}
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component class DemoAgentRuntime implements AgentRuntime {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public AgentResult run(AgentRequest request){
  return new AgentResult("invoice-audit-demo","已完成票面识别、重复风险和四单匹配检查，异常票据等待财务审核专员确认。",List.of(new AgentStep("票面识别","COMPLETED","结构化 14 个关键字段"),new AgentStep("四单匹配","COMPLETED","关联合同、订单、收货与付款"),new AgentStep("异常复核","PENDING","等待财税规则确认")),Map.of("evidenceItems",11,"suggestedActions",3,"objectiveLength",request.objective().length()));
 }
}
