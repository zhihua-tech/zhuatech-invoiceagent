/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.invoiceagent.workbench;

import static cn.zhuatech.invoiceagent.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);
    keys(r, "declaredTotal", "tolerance", "lines");
    BigDecimal declared = money(r, "declaredTotal"), tolerance = decimal(r, "tolerance", 0, 1);
    Set<String> ids = new HashSet<>();
    BigDecimal net = BigDecimal.ZERO, tax = BigDecimal.ZERO;
    List<Map<String, Object>> out = new ArrayList<>();
    for (var line : rows(r, "lines", 1, 300)) {
      keys(line, "id", "quantity", "unitPrice", "taxRate");
      String id = id(line, "id");
      unique(ids, id);
      BigDecimal
          amount =
              decimal(line, "quantity", 0.000001, 1e6)
                  .multiply(decimal(line, "unitPrice", 0, 1e9))
                  .setScale(2, RoundingMode.HALF_UP),
          vat = amount.multiply(decimal(line, "taxRate", 0, 1)).setScale(2, RoundingMode.HALF_UP);
      net = net.add(amount);
      tax = tax.add(vat);
      out.add(Map.of("id", id, "net", amount, "tax", vat, "gross", amount.add(vat)));
    }
    BigDecimal total = net.add(tax), difference = total.subtract(declared);
    return Map.of(
        "netTotal",
        net,
        "taxTotal",
        tax,
        "calculatedTotal",
        total,
        "declaredTotal",
        declared,
        "difference",
        difference,
        "matches",
        difference.abs().compareTo(tolerance) <= 0,
        "rounding",
        "PER_LINE_HALF_UP_2",
        "lines",
        out);
  }
}
