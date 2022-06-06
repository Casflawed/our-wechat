/**
 * user——message 一对多
 * user——group  多对多
 * user——friend 一对多
 * group——meesage 一对多
 */
-- user表
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(50) DEFAULT NULL COMMENT '用户地址',
  `avatar_url` varchar(50) DEFAULT NULL COMMENT '用户头像',
  `create_date` datetime DEFAULT NULL COMMENT '注册日期',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `password` varchar(16) NOT NULL COMMENT '密码',
  `signature` varchar(50) DEFAULT NULL COMMENT '个人签名',
  `wexin_id` varchar(12) NOT NULL COMMENT '微信号（登录用它或邮箱）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- message表
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) NOT NULL COMMENT '消息内容',
  `create_time` datetime DEFAULT NULL COMMENT '消息发送时间',
  `from_user_id` bigint(20) NOT NULL COMMENT '发出消息的成员Id',
  `group_id` bigint(20) NOT NULL COMMENT '接受消息的群组',
  `message_type` tinyint(4) NOT NULL COMMENT '消息类型',
  `status` bit(1) NOT NULL COMMENT '消息已读状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- group_user表
CREATE TABLE `group_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL COMMENT '群组Id',
  `user_id` bigint(20) NOT NULL COMMENT '群组内成员Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- group表
CREATE TABLE `group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(50) DEFAULT NULL COMMENT '群组头像URL',
  `create_date` datetime DEFAULT NULL COMMENT '群组创建日期',
  `name` varchar(50) NOT NULL COMMENT '群组名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- friend表
CREATE TABLE `friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accept_date` datetime DEFAULT NULL COMMENT '成为好友的日期',
  `avatar_url` varchar(50) DEFAULT NULL COMMENT '好友头像URL',
  `friend_id` bigint(20) NOT NULL COMMENT '好友Id',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `self_id` bigint(20) NOT NULL COMMENT '本人Id',
  `status` int(11) NOT NULL COMMENT '好友申请状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;