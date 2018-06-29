INSERT INTO `chick`.`permission` (`id`, `permission_name`, `permission_key`, `permission_url`, `parent_id`, `sort_id`, `gmt_modified`, `gmt_create`) VALUES ('42', '签到管理配置', 'pray:detail', 'sign/sign_detail.html', '25', '1', '2017-04-19 16:22:48', '2017-04-19 16:22:48');
INSERT INTO `permission` VALUES (43, '任务奖励管理', 'taskRewardsManage', NULL, 0, 5, '2018-06-15 17:21:19', '2018-06-15 17:21:21');
INSERT INTO `permission` VALUES (44, '任务奖励配置', 'taskRewardsConfig:list', 'taskRewards/taskRewards-list.html', 43, 999, '2018-06-15 17:22:27', '2018-06-15 17:22:29');
INSERT INTO `permission` VALUES (45, '保存任务奖励配置', 'taskRewardsConfig:add', NULL, 44, 999, '2018-06-15 17:23:09', '2018-06-15 17:23:11');
