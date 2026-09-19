/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent;import cn.zhuatech.invoiceagent.service.InvoiceDuplicateRiskService;import org.junit.jupiter.api.Test;import java.math.BigDecimal;import java.time.LocalDate;import java.util.List;import static org.assertj.core.api.Assertions.assertThat;
class InvoiceDuplicateRiskServiceTests{private final InvoiceDuplicateRiskService s=new InvoiceDuplicateRiskService();
 @Test void clearsUniqueInvoice(){assertThat(s.assess(req("INV-2","900",false,true,List.of())).decision()).isEqualTo(InvoiceDuplicateRiskService.Decision.CLEAR);}
 @Test void reviewsNearDuplicateAndBankChange(){var r=s.assess(req("INV-2","1000",true,false,List.of(c("OLD","INV-X","1000",-3))));assertThat(r.decision()).isEqualTo(InvoiceDuplicateRiskService.Decision.REVIEW);assertThat(r.riskSignals()).hasSize(2);}
 @Test void blocksExactDuplicate(){var r=s.assess(req("INV-1","1000",false,true,List.of(c("OLD","INV-1","1000",-30))));assertThat(r.decision()).isEqualTo(InvoiceDuplicateRiskService.Decision.BLOCK);}
 private InvoiceDuplicateRiskService.Request req(String no,String amount,boolean changed,boolean verified,List<InvoiceDuplicateRiskService.Candidate>c){return new InvoiceDuplicateRiskService.Request("NEW",no,"TAX-A",new BigDecimal(amount),LocalDate.of(2026,9,19),true,changed,verified,7,c);}
 private InvoiceDuplicateRiskService.Candidate c(String id,String no,String amount,int days){return new InvoiceDuplicateRiskService.Candidate(id,no,"TAX-A",new BigDecimal(amount),LocalDate.of(2026,9,19).plusDays(days));}}
