-- tsukumi.dic_info definition

CREATE TABLE `dic_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `p_code` varchar(100) DEFAULT NULL,
  `del_flag` bit(1) NOT NULL DEFAULT b'0',
  `attribute_1` varchar(100) DEFAULT NULL,
  `attribute_2` varchar(100) DEFAULT NULL,
  `attribute_3` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dic_info_UN` (`code`,`type`,`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典表';


-- tsukumi.grumble_info definition

CREATE TABLE `grumble_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `content` longtext,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` bit(1) NOT NULL DEFAULT b'0',
  `type` varchar(20) NOT NULL,
  `mode` varchar(100) DEFAULT NULL COMMENT '心情：dic',
  `islike` int DEFAULT '0' COMMENT '赞',
  `repost` int DEFAULT '0' COMMENT '转发数',
  `reply_post` int DEFAULT NULL COMMENT '被评论id-该条消息为评论时存在',
  `repost_id` int DEFAULT NULL COMMENT '被转发id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='吐槽内容';


-- tsukumi.oauth_access_token definition

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` mediumblob,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tsukumi.oauth_approvals definition

CREATE TABLE `oauth_approvals` (
  `userid` varchar(255) DEFAULT NULL,
  `clientid` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresat` timestamp NULL DEFAULT NULL,
  `lastmodifiedat` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tsukumi.oauth_client_details definition

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tsukumi.oauth_client_token definition

CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tsukumi.oauth_code definition

CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tsukumi.oauth_refresh_token definition

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tsukumi.tag_info definition

CREATE TABLE `tag_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `mode` varchar(100) DEFAULT NULL COMMENT '心情：dic_info',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tag_info_UN` (`name`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签表';


-- tsukumi.user_info definition

CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';


-- tsukumi.group_info definition

CREATE TABLE `group_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL COMMENT '聊天组',
  `creator` int DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `last_spoke` datetime DEFAULT NULL COMMENT '最后活动时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动组';


-- tsukumi.group_grumble definition

CREATE TABLE `group_grumble` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `grumble_id` int NOT NULL,
  `creator` int NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组消息';


INSERT INTO tsukumi.dic_info (id, code, name, type, p_code, del_flag, attribute_1, attribute_2, attribute_3, create_time, update_time) VALUES (1, 'anonymous', '匿名', 'grumbleType', '', false, '', '', '', '2021-01-13 03:55:18', '2021-01-13 03:55:18');
INSERT INTO tsukumi.dic_info (id, code, name, type, p_code, del_flag, attribute_1, attribute_2, attribute_3, create_time, update_time) VALUES (2, 'private', '私人', 'grumbleType', '', false, '', '', '', '2021-01-13 03:57:22', '2021-01-13 03:57:22');
INSERT INTO tsukumi.dic_info (id, code, name, type, p_code, del_flag, attribute_1, attribute_2, attribute_3, create_time, update_time) VALUES (3, 'public', '广场', 'grumbleType', '', false, '', '', '', '2021-01-13 03:57:29', '2021-01-13 03:57:29');