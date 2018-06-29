CREATE TABLE `gift` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(50) NOT NULL COMMENT '名称',
  `img_path` VARCHAR(200) NOT NULL COMMENT '图片',
  `price` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT '0.00' COMMENT '价格',
  `inventory` INT(8) UNSIGNED NOT NULL DEFAULT '0' COMMENT '库存',
  `status` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '1--有效，2--无效',
  `type` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '1--实体，2--虚拟物品',
  `egg_quantity` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '兑换鸡蛋数量',
  `description` VARCHAR(200) NOT NULL COMMENT '礼品描述',
  `gmt_modified` DATETIME NOT NULL COMMENT '修改时间',
  `gmt_create` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
COMMENT='兑换礼品表'
;
alter table  chick.egg_exchange_record add column `gift_id` BIGINT(20) UNSIGNED NULL DEFAULT '0' COMMENT '礼品id' after amount;
alter table  chick.egg_exchange_record add column `gift_name` VARCHAR(50) NULL COMMENT '礼品名称' after gift_id;
alter table  chick.egg_exchange_record add column `gift_img_path` VARCHAR(200) NULL COMMENT '礼品图片' after gift_name;
alter table  chick.egg_exchange_record modify column `egg_quantity` decimal(20,4) NOT NULL DEFAULT '0.0000' COMMENT '兑换鸡蛋数量';