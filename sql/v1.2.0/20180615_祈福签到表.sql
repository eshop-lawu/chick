CREATE TABLE `pray_sign_rule` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_num` varchar(20) DEFAULT NULL COMMENT '日常获得商品编号',
  `product_count` int(8) DEFAULT '0' COMMENT '每日奖励数量',
  `is_basis_chick` tinyint(1) DEFAULT '0' COMMENT '是否根据小鸡数量',
  `day` int(3) DEFAULT '0' COMMENT '连续签到天数',
  `extra` varchar(500) DEFAULT NULL COMMENT 'json，额外奖励规则',
  `status` tinyint(2) DEFAULT '0' COMMENT '状态，0禁用，1启用',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='祈福签到配置';

CREATE TABLE `pray_sign_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_id` bigint(20) unsigned NOT NULL COMMENT '关联配置id',
  `member_num` varchar(20) DEFAULT NULL COMMENT '用户编号',
  `sign_time` date DEFAULT NULL COMMENT '签到时间',
  `take_time` datetime DEFAULT NULL COMMENT '领取时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_member_num_sign_time` (`member_num`,`sign_time`) USING BTREE
) COMMENT='祈福签到记录';

CREATE TABLE `pray_sign_rewards` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sign_id` bigint(20) unsigned NOT NULL COMMENT '签到记录ID',
  `member_num` varchar(20) DEFAULT NULL COMMENT '用户编号',
  `is_extra` tinyint(1) DEFAULT '0' COMMENT '是否额外奖励 0否，1是',
  `product_num` varchar(20) DEFAULT NULL COMMENT '商品编号',
  `product_count` int(8) DEFAULT '0' COMMENT '奖励数量',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='祈福签到奖励表';