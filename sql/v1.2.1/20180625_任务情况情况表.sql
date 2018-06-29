CREATE TABLE `task_record` (
`id`  bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
`user_num`  varchar(20) NULL COMMENT '用户编号' ,
`type`  tinyint(2) NULL COMMENT '类型，1关注,2邀请' ,
`gmt_create`  datetime NULL ,
`gmt_modified`  datetime NULL ,
PRIMARY KEY (`id`)
)
COMMENT='任务完成情况记录';