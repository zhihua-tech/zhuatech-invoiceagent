/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
export const domain={
  "code": "Invoice Agent",
  "systemName": "企业发票审核智能体平台",
  "englishName": "INVOICE AUDIT AGENT",
  "theme": {
    "primary": "#4b5f78",
    "dark": "#25303f",
    "accent": "#b88a3d"
  },
  "workspace": "财务共享中心 / 发票审核",
  "fieldWorkspace": "审核专员工作台 · 应付组",
  "period": "2026-08-08 · 今日审核批次",
  "liveText": "税务规则于 10:34 完成同步",
  "fieldContextLabel": "审核范围",
  "fieldContext": "增值税专票 · 普票 · 电子票",
  "fieldUser": "宋屿",
  "fieldRole": "发票审核专员",
  "adminUser": "许岚",
  "adminRole": "财务共享负责人",
  "adminTitle": "发票智能体运营中心",
  "adminBreadcrumb": "发票审核 / 风险协同",
  "adminSubtitle": "连接发票、合同、订单、收货和付款申请，自动检查重复与税务异常，入账仍由财务人员确认。",
  "exportAction": "导出审核日报",
  "createAction": "导入发票批次",
  "chartTitle": "审核完成率",
  "chartSubtitle": "发票批次处理与异常闭环",
  "chartLabels": [
    "周一",
    "周二",
    "周三",
    "周四",
    "周五",
    "周六",
    "周日",
    "下周一",
    "下周二"
  ],
  "loadTitle": "票据与单据就绪度",
  "loadSubtitle": "票面、合同、订单、收货与税务规则",
  "recordsTitle": "发票审核队列",
  "recordsSubtitle": "按金额、到期日、重复和税务风险排序",
  "issueTitle": "重复与税务异常",
  "issueSubtitle": "重复票、合同不匹配和税务异常不得自动入账",
  "recordName": "发票批次",
  "itemName": "供应商 / 发票批次",
  "unitName": "审核团队",
  "batchName": "审核阶段",
  "planName": "计划步骤",
  "doneName": "已完成",
  "exceptionName": "异常发票",
  "unitLabel": "项",
  "listBreadcrumb": "财务共享 / 审核队列",
  "listSubtitle": "统一管理票面识别、重复检查、四单匹配、税务核验、人工复核和入账回写。",
  "listSummary": [
    [
      "在途分析",
      "47"
    ],
    [
      "本周交付",
      "32"
    ],
    [
      "业务待确认",
      "9"
    ],
    [
      "敏感待审",
      "6",
      true
    ]
  ],
  "tabs": [
    "全部",
    "理解中",
    "票据核验中",
    "待确认",
    "已交付"
  ],
  "fieldBreadcrumb": "分析工作台 / 我的任务",
  "fieldTitle": "发票审核专员协同工作台",
  "fieldSubtitle": "120 张待审核 · 6 张异常票 · 审核专员宋屿",
  "fieldSecondary": "查看核验规则地图",
  "reportAction": "确认分析结论",
  "fieldNoticeTitle": "智能体已匹配已核验规则和只读票据资料集",
  "fieldNotice": "禁止执行写操作；敏感字段、外发报告与关键结论必须确认",
  "steps": [
    "问题澄清",
    "核验规则匹配",
    "票据只读核验",
    "结论解释",
    "人工确认"
  ],
  "documentAction": "打开分析底稿",
  "printAction": "查看票据资料单据链路",
  "resourceCardTitle": "核验规则覆盖",
  "resourceValueLabel": "可用核验规则",
  "resourceHealthLabel": "票据资料可信度",
  "quickSubtitle": "发票审核专员常用协同入口",
  "quickActions": [
    [
      "自然语言分析",
      "/shopfloor/report",
      "问题、范围与税务规则"
    ],
    [
      "核验规则目录",
      "/shopfloor/material",
      "定义、负责人和版本"
    ],
    [
      "票据资料单据链路",
      "/shopfloor/resources",
      "来源、转换与质量"
    ],
    [
      "风险升级",
      "/shopfloor/andon",
      "敏感、越权与低置信度"
    ]
  ],
  "reportDefaults": [
    1,
    0
  ],
  "reportTitle": "确认发票智能体分析结论",
  "reportSubtitle": "记录问题税务规则、票据资料范围、关键发现、限制条件和业务行动。",
  "reportSuccess": "分析结论已进入费用洞察目录",
  "reportPlaceholder": "填写业务问题、核验规则税务规则、结论限制、行动建议或修正意见",
  "reportFootnote": "智能体只执行只读分析，不得修改生产票据资料或绕过票据资料权限",
  "ruleTitle": "发票智能体票据核验策略",
  "ruleSubtitle": "DATA-QUERY-GUARD · V1.0",
  "rules": [
    [
      "票据核验方式",
      "只读"
    ],
    [
      "核验规则税务规则",
      "认证优先"
    ],
    [
      "敏感字段",
      "自动脱敏"
    ],
    [
      "报告外发",
      "人工确认",
      true
    ]
  ],
  "fieldTotals": [
    [
      "5",
      "重点问题"
    ],
    [
      "3",
      "今日报告"
    ],
    [
      "96%",
      "核验规则匹配"
    ],
    [
      "7",
      "分析建议"
    ]
  ],
  "adminMenus": [
    [
      "/admin",
      "home",
      "分析驾驶舱"
    ],
    [
      "/admin/work-orders",
      "order",
      "分析队列"
    ],
    [
      "/admin/samples",
      "box",
      "核验规则目录"
    ],
    [
      "/admin/schedule",
      "calendar",
      "报告日历"
    ],
    [
      "/admin/methods",
      "process",
      "分析模板"
    ],
    [
      "/admin/reviews",
      "quality",
      "结论评测"
    ],
    [
      "/admin/resources",
      "machine",
      "票据资料与权限"
    ],
    [
      "/admin/report",
      "chart",
      "服务分析"
    ]
  ],
  "fieldMenus": [
    [
      "/shopfloor",
      "home",
      "我的工作台"
    ],
    [
      "/shopfloor/report",
      "report",
      "结论确认"
    ],
    [
      "/shopfloor/tasks",
      "order",
      "发票批次"
    ],
    [
      "/shopfloor/material",
      "box",
      "核验规则目录"
    ],
    [
      "/shopfloor/resources",
      "machine",
      "票据资料单据链路"
    ],
    [
      "/shopfloor/andon",
      "risk",
      "风险升级",
      6
    ]
  ],
  "moduleTitles": {
    "tasks": [
      "发票批次",
      "跟踪问题、税务规则、票据核验、结论与交付"
    ],
    "material": [
      "核验规则目录",
      "查看核验规则的定义、负责人和版本"
    ],
    "resources": [
      "票据资料单据链路",
      "了解票据资料来源、加工逻辑、质量和刷新状态"
    ],
    "andon": [
      "票据资料风险升级",
      "上报敏感、越权、税务规则冲突和低置信度结果"
    ],
    "samples": [
      "核验规则目录",
      "统一维护费用核验规则与票据资料产品"
    ],
    "schedule": [
      "报告日历",
      "管理周期报告、订阅与交付时点"
    ],
    "methods": [
      "分析模板",
      "沉淀问题框架、核验规则 模板和解释规范"
    ],
    "reviews": [
      "结论评测",
      "评估票据核验正确、税务规则一致和解释可靠"
    ],
    "report": [
      "服务分析",
      "分析请求量、交付效率与业务采纳"
    ]
  },
  "tagline": "让每一张发票都能关联业务事实与审核责任",
  "storyTitle": "先核票面，<br/>再匹配业务单据。",
  "storyText": "智能体负责检索、票据核验和形成分析草稿；票据资料团队负责验证与发布。",
  "pattern": [
    2,
    4,
    7,
    9,
    12,
    16,
    19,
    21,
    25,
    28,
    31,
    34
  ],
  "loginStats": [
    [
      "47",
      "在途分析"
    ],
    [
      "96%",
      "核验规则匹配"
    ],
    [
      "6",
      "敏感待审"
    ]
  ],
  "loginTitle": "发票审核智能体平台",
  "adminDemo": "核验规则 / 票据核验 / 评测",
  "fieldDemo": "问题 / 税务规则 / 结论"
}
export const records=[
  {
    "no": "INV-260808-018",
    "name": "云服务采购专票批次",
    "code": "INVOICE-SERVICE",
    "unit": "发票审核组",
    "group": "财务共享中心",
    "plan": 10,
    "done": 6,
    "exception": 2,
    "due": "11:50",
    "batch": "结论解释",
    "status": "待审核",
    "progress": 60,
    "priority": "关注"
  },
  {
    "no": "INV-260808-012",
    "name": "七月物流费用发票",
    "code": "INVOICE-LOGISTICS",
    "unit": "财税规则组",
    "group": "财务共享中心",
    "plan": 8,
    "done": 8,
    "exception": 0,
    "due": "10:10",
    "batch": "报告发布",
    "status": "已完成",
    "progress": 100,
    "priority": "正常"
  },
  {
    "no": "INV-260808-021",
    "name": "生产设备预付款发票",
    "code": "INVOICE-EQUIPMENT",
    "unit": "采购结算组",
    "group": "运营中心",
    "plan": 9,
    "done": 4,
    "exception": 1,
    "due": "14:20",
    "batch": "票据只读核验",
    "status": "进行中",
    "progress": 44,
    "priority": "正常"
  },
  {
    "no": "INV-260808-027",
    "name": "咨询服务费重复票风险",
    "code": "INVOICE-DUPLICATE",
    "unit": "发票审核组",
    "group": "财务共享中心",
    "plan": 11,
    "done": 3,
    "exception": 4,
    "due": "16:10",
    "batch": "权限审核",
    "status": "风险跟进",
    "progress": 27,
    "priority": "加急"
  }
]
export const resources=[
  {
    "code": "INV-OCR-01",
    "name": "发票票面识别服务",
    "unit": "财税规则组",
    "status": "运行中",
    "health": 97,
    "value": "286",
    "valueUnit": "个",
    "note": "核验规则定义与负责人均已登记"
  },
  {
    "code": "INV-MATCH-02",
    "name": "合同订单收货匹配器",
    "unit": "财务系统组",
    "status": "运行中",
    "health": 94,
    "value": "42",
    "valueUnit": "个",
    "note": "10:30 完成票据资料新鲜度检查"
  },
  {
    "code": "INV-RISK-03",
    "name": "重复与税务异常审查器",
    "unit": "财税风控组",
    "status": "复核",
    "health": 79,
    "value": "6",
    "valueUnit": "项",
    "note": "6 个敏感票据核验等待确认"
  }
]
export const reviews=[
  {
    "no": "REV-INV-028",
    "title": "云服务发票重复风险复核",
    "type": "异常表述",
    "detail": "18 个切片 · 许岚",
    "result": "待确认"
  },
  {
    "no": "REV-INV-017",
    "title": "今日审核批次税务规则一致性核验",
    "type": "核验规则准确性",
    "detail": "32 个核验规则 · 宋屿",
    "result": "通过"
  },
  {
    "no": "REV-INV-039",
    "title": "设备预付款税务规则复核",
    "type": "票据资料合规",
    "detail": "发现 4 个敏感字段",
    "result": "异常"
  }
]
export const adminMetrics=[
  [
    "今日入池发票",
    "428",
    "含专票 316 张",
    "blue"
  ],
  [
    "自动审核通过率",
    "91.8%",
    "平均 7.4 秒完成核验",
    "green"
  ],
  [
    "四单匹配率",
    "87.5%",
    "28 张缺少收货记录",
    "orange"
  ],
  [
    "异常待复核",
    "14",
    "3 张疑似重复开票",
    "red"
  ]
]
export const fieldMetrics=[
  [
    "我的审核任务",
    "18",
    "首批 11:50 前完成",
    "blue"
  ],
  [
    "票面识别完整率",
    "98.6%",
    "14 个关键字段已提取",
    "green"
  ],
  [
    "异常待确认",
    "7",
    "2 张命中重复票规则",
    "orange"
  ],
  [
    "税务规则版本",
    "良好",
    "已同步 08-07 规则包",
    "slate"
  ]
]
export const chartActual=[
  68,
  72,
  75,
  79,
  81,
  84,
  88,
  90,
  92
]
export const chartTarget=[
  72,
  75,
  78,
  81,
  84,
  87,
  90,
  92,
  94
]
export const loads=[
  [
    "发票验真与查重",
    97,
    "428 张已验真 · 3 张待确认"
  ],
  [
    "合同与采购订单",
    94,
    "396 张已关联 · 18 张缺合同"
  ],
  [
    "收货与付款申请",
    89,
    "374 张已匹配 · 28 张待补录"
  ],
  [
    "财税敏感字段",
    74,
    "6 类敏感信息 · 4 项待授权"
  ]
]
export const issues=[
  {
    "type": "税务规则",
    "title": "同一销方、金额与开票日期命中重复票规则",
    "detail": "建议冻结入账并核对原始发票代码与号码",
    "status": "待确认"
  },
  {
    "type": "权限",
    "title": "设备预付款发票尚未关联验收记录",
    "detail": "四单匹配已暂停，等待业务经办人补充材料",
    "status": "复核中"
  },
  {
    "type": "解释",
    "title": "税率与采购合同约定存在 3 个百分点差异",
    "detail": "当前资料不足以自动入账，已转财税专员复核",
    "status": "补充中"
  }
]
