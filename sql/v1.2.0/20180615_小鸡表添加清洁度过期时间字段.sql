ALTER TABLE `chicken`
ADD COLUMN `cleanness_invalid_time`  datetime NULL COMMENT '清洁度过期时间' AFTER `house_unclean_duration`;

update `chicken` set `cleanness_invalid_time`=now();