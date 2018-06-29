ALTER TABLE `chick`.`product`
ADD COLUMN `joyful_limit` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '愉悦值增加次数上限(0为不限制)' AFTER `is_joyful_first`,
ADD COLUMN `growth_limit` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '成长值增加次数上限(0为不限制)' AFTER `is_growth_first`,
ADD COLUMN `full_limit` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '饱腹值增加次数上限(0为不限制)' AFTER `is_full_first`,
ADD COLUMN `keep_clean_time` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '保持清洁度时间（单位：天）' AFTER `day_usage_limit`,
ADD COLUMN `clean_limit` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '清洁保持时间增加次数上限(0为不限制)' AFTER `keep_clean_time`,
ADD COLUMN `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否在商店显示' AFTER `clean_limit`;


ALTER TABLE `chick`.`inventory`
ADD COLUMN `joyful_limit` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '愉悦值增加次数上限(0为不限制)' AFTER `is_joyful_first`,
ADD COLUMN `growth_limit` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '成长值增加次数上限(0为不限制)' AFTER `is_growth_first`,
ADD COLUMN `full_limit` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '饱腹值增加次数上限(0为不限制)' AFTER `is_full_first`,
ADD COLUMN `keep_clean_time` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '保持清洁度时间（单位：天）' AFTER `day_usage_limit`,
ADD COLUMN `clean_limit` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '清洁保持时间增加次数上限(0为不限制)' AFTER `keep_clean_time`;

UPDATE `chick`.`product` SET joyful_limit = is_joyful_first, growth_limit = is_growth_first, full_limit = is_full_first, is_show = 1;
UPDATE `chick`.`inventory` SET joyful_limit = is_joyful_first, growth_limit = is_growth_first, full_limit = is_full_first;