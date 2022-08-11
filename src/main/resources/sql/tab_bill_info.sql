
DROP TABLE IF EXISTS `tab_bill_info`;
CREATE TABLE `tab_bill_info` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `trade_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '交易单号',
  `merchant_code` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商户单号',
  `name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `account` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '交易账号',
  `trade_type` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '交易类型',
  `trade_man` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '交易对方',
  `trade_account` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '交易方账号',
  `trade_goods` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品说明',
  `in_or_out` varchar(8) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '收/支',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '金额(元)',
  `pay_way` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '支付方式',
  `trade_platform` varchar(8) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '交易平台',
  `trade_status` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '当前状态',
  `trade_time` datetime DEFAULT NULL COMMENT '交易时间',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账单表';
