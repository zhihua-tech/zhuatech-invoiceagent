/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;
import java.math.BigDecimal;import java.time.LocalDate;import java.time.temporal.ChronoUnit;import java.util.*;
/**
 * 在付款前识别精确重复票、拆分/近似重复和供应商账户变更风险。
 *
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class InvoiceDuplicateRiskService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public Result assess(Request r){List<String>blockers=new ArrayList<>(),signals=new ArrayList<>(),matches=new ArrayList<>();
  if(!r.taxValidationPassed())blockers.add("发票税务校验未通过");
  if(r.bankAccountChanged()&&!r.bankChangeVerified())signals.add("供应商收款账户近期变更且未完成回拨验证");
  for(Candidate c:r.recentInvoices()){
   boolean sameIdentity=r.supplierTaxId().equals(c.supplierTaxId());boolean sameAmount=r.amount().compareTo(c.amount())==0;
   if(sameIdentity&&r.invoiceNumber().equalsIgnoreCase(c.invoiceNumber())){blockers.add("发现相同供应商和发票号码: "+c.invoiceId());matches.add(c.invoiceId());}
   else if(sameIdentity&&sameAmount&&Math.abs(ChronoUnit.DAYS.between(r.invoiceDate(),c.invoiceDate()))<=r.nearDuplicateDays()){signals.add("发现同供应商同金额近似重复: "+c.invoiceId());matches.add(c.invoiceId());}
  }
  Decision d=!blockers.isEmpty()?Decision.BLOCK:!signals.isEmpty()?Decision.REVIEW:Decision.CLEAR;
  List<String>actions=List.of(d==Decision.BLOCK?"冻结入账与付款，转应付会计核验原始票据":d==Decision.REVIEW?"挂起付款并复核合同、收货、账户变更与历史票据":"允许进入后续三单匹配与付款门禁");
  return new Result(d,List.copyOf(new LinkedHashSet<>(matches)),List.copyOf(blockers),List.copyOf(signals),actions);
 }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Request(@NotBlank String invoiceId,@NotBlank String invoiceNumber,@NotBlank String supplierTaxId,@DecimalMin("0.01")BigDecimal amount,@NotNull LocalDate invoiceDate,boolean taxValidationPassed,boolean bankAccountChanged,boolean bankChangeVerified,@Min(1)@Max(365)int nearDuplicateDays,@NotNull List<@Valid Candidate>recentInvoices){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Candidate(@NotBlank String invoiceId,@NotBlank String invoiceNumber,@NotBlank String supplierTaxId,@DecimalMin("0.01")BigDecimal amount,@NotNull LocalDate invoiceDate){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Result(Decision decision,List<String>matchedInvoiceIds,List<String>blockers,List<String>riskSignals,List<String>actions){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public enum Decision{CLEAR,REVIEW,BLOCK}
}
