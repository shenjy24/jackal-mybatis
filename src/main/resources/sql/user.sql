-- auto Generated on 2021-07-06
-- DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(32) NOT NULL COMMENT '玩家名',
  `user_age` tinyint(11) NOT NULL DEFAULT '1' COMMENT '玩家年龄',
  `user_status` tinyint(11) NOT NULL DEFAULT '1' COMMENT '玩家状态',
  `user_score` varchar(255) NOT NULL DEFAULT '0' COMMENT '玩家积分',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  `utime` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  KEY `idx_name` (`user_name`),
  KEY `idx_utime` (`utime`),
  KEY `idx_score` (`user_score`),
  KEY `idx_utime_score` (`utime`,`user_score`),
  KEY `idx_utime_score_id_name` (`utime`,`user_score`,`user_id`,`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=20021 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';
