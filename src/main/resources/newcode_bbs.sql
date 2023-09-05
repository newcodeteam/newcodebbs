CREATE DATABASE IF NOT EXISTS newcode_bbs CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';

USE newcode_bbs;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `user_data`;
DROP TABLE IF EXISTS `user_type`;
DROP TABLE IF EXISTS `type_group`;
DROP TABLE IF EXISTS `user_fans_one`;
DROP TABLE IF EXISTS `user_fans_two`;
DROP TABLE IF EXISTS `user_chat_one`;
DROP TABLE IF EXISTS `user_chat_two`;
DROP TABLE IF EXISTS `file_data`;
DROP TABLE IF EXISTS `category_data`;
DROP TABLE IF EXISTS `tag_data`;
DROP TABLE IF EXISTS `recommend_data`;
DROP TABLE IF EXISTS `vip_data`;
DROP TABLE IF EXISTS `comments_data`;
DROP TABLE IF EXISTS `postings_data`;
DROP TABLE IF EXISTS `postings_info`;
DROP TABLE IF EXISTS `postings_other`;
DROP TABLE IF EXISTS `user_analyse_data`;
DROP TABLE IF EXISTS `analyse_data`;
DROP TABLE IF EXISTS `order_bill_recharge`;
DROP TABLE IF EXISTS `order_bill_credits`;
DROP TABLE IF EXISTS `order_bill_golds`;
DROP TABLE IF EXISTS `user_token`;
DROP TABLE IF EXISTS `cache`;
DROP TABLE IF EXISTS `website_data`;


#----------------------
# 用户表
#----------------------
CREATE TABLE `user_data`  (
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
      `user_mail` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮箱',
      `user_pwd` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
      `user_concern` int(3) UNSIGNED NULL DEFAULT 0 COMMENT '关注数量',
      `user_fans` int(8) UNSIGNED NULL DEFAULT 0 COMMENT '粉丝数量',
      `user_qq` char(15) NULL DEFAULT '' COMMENT 'QQ',
      `user_credits` int(11) NULL DEFAULT 0 COMMENT '积分',
      `user_golds` int(11) NULL DEFAULT 0 COMMENT '预留 虚拟币',
      `user_rmbs` int(11) NULL DEFAULT 0 COMMENT '人民币',
      `user_create_ip` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '创建时IP',
      `user_login_ip` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '登录时IP',
      `user_article` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '文章数量',
      `user_comments` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '评论数量',
      `user_status` tinyint(1) DEFAULT 0 COMMENT '用户是否封禁 0 正常',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '登陆时间',
      PRIMARY KEY (`id`) USING BTREE,
      UNIQUE (user_id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '用户表';


#----------------------
# 权限表
#----------------------
CREATE TABLE `user_type`  (
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `user_type_nickname` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '头衔昵称,多个昵称以,隔开',
      `user_type_id` int(3) UNSIGNED NULL DEFAULT 0 COMMENT '权限id,默认0 游客组 无权限',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '权限表';

#----------------------
# 权限表 0 为空 1 代表有权限
#----------------------
CREATE TABLE `type_group`  (
      `user_type_id` int(6) UNSIGNED NOT NULL COMMENT '权限Id',
      `type_name` char(20) NOT NULL default '' COMMENT '权限名称',
      `type_all` int(11) NULL default 0 COMMENT '任何权限',
      `type_credits_from` int(11) NULL default 0 COMMENT '编辑积分权限',
      `type_golds_from` int(11) NULL default 0 COMMENT '编辑虚拟币权限',
      `type_allow_read` int(11) NULL default 0 COMMENT '允许访问权限',
      `type_allow_article` int(11) NULL default 0 COMMENT '允许发帖子权限',
      `type_allow_comments` int(11) NULL default 0 COMMENT '允许评论权限',
      `type_allow_attach` int(11) NULL default 0 COMMENT '允许上传文件权限',
      `type_allow_down` int(11) NULL default 0 COMMENT '允许下载文件权限',
      `type_allow_top` int(11) NULL default 0 COMMENT '允许顶置帖子权限',
      `type_allow_update` int(11) NULL default 0 COMMENT '允许更改帖子权限',
      `type_allow_delete` int(11) NULL default 0 COMMENT '允许删除帖子权限',
      `type_allow_move` int(11) NULL default 0 COMMENT '允许移动帖子板块权限',
      `type_allow_ban_user` int(11) NULL default 0 COMMENT '允许禁止用户权限',
      `type_allow_delete_user` int(11) NULL default 0 COMMENT '允许删除用户权限',
      `type_allow_view_ip` int(11) unsigned NULL default 0 COMMENT '允许查看用户敏感信息权限',
      `type_allow_category` int(11) unsigned NULL default 0 COMMENT '所属板块id',
      PRIMARY KEY (`user_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '权限表 0 为空 1 代表有权限';

INSERT INTO `type_group` SET user_type_id='0', type_name='用户组', type_allow_read=1, type_allow_article=1, type_allow_comments=1, type_allow_attach=1, type_allow_down=1;
INSERT INTO `type_group` SET user_type_id='1', type_name='管理员组', type_all=1;

#----------------------
# 关注与粉丝表 < 5000 id
#----------------------
CREATE TABLE `user_fans_one`  (
     `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
     `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '粉丝id',
     `user_fans_followed_id` bigint(20) UNSIGNED NOT NULL COMMENT '关注的用户id',
     `user_fans_status` tinyint(1) DEFAULT '0' COMMENT '关注状态(0关注 1取消)',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE KEY `user_followed_indx` (`user_id`,`user_fans_followed_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '关注与粉丝表 < 5000 id';

#----------------------
# 关注与粉丝表 > 5000 id
#----------------------
CREATE TABLE `user_fans_two`  (
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '粉丝id',
      `user_fans_followed_id` bigint(20) UNSIGNED NOT NULL COMMENT '关注的用户id',
      `user_fans_status` tinyint(1) DEFAULT '0' COMMENT '关注状态(0关注 1取消)',
      PRIMARY KEY (`id`) USING BTREE,
      UNIQUE KEY `user_followed_index` (`user_id`,`user_fans_followed_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '关注与粉丝表 > 5000 id';

#----------------------
# 聊天表 < 300000 数据
#----------------------
CREATE TABLE `user_chat_one`  (
     `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
     `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
     `user_chat_id` bigint(20) UNSIGNED NOT NULL COMMENT '跟聊天者的id',
     `user_chat_text` text COMMENT '聊天内容',
     `user_chat_text_status` tinyint(1) NULL DEFAULT 0 COMMENT '是否是发送文件或者图片 0空 1文件 2图片',
     `user_chat_addr` bigint(20) NULL DEFAULT 0 COMMENT '文件或者图片id',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '聊天表 < 300000 数据';

#----------------------
# 聊天表 > 300000 数据
#----------------------
CREATE TABLE `user_chat_two`  (
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `user_chat_id` bigint(20) UNSIGNED NOT NULL COMMENT '跟聊天者的id',
      `user_chat_text` text COMMENT '聊天内容',
      `user_chat_text_status` tinyint(1) NULL DEFAULT 0 COMMENT '是否是发送文件或者图片 0空 1文件 2图片',
      `user_chat_addr` bigint(20) NULL DEFAULT 0 COMMENT '文件或者图片id',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '聊天表 > 300000 数据';

#----------------------
# 文件和图片表
#----------------------
CREATE TABLE `file_data`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `file_addr` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 0 COMMENT '文件或者图片地址',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '文件和图片表';

#----------------------
# 分类板块表
#----------------------
CREATE TABLE `category_data`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `category_id` bigint(20) UNSIGNED NOT NULL COMMENT '分类id',
      `category_name` varchar(255) NOT NULL COMMENT '分类名称',
      `category_english_name` varchar(255) NOT NULL COMMENT '分类英文名',
      `category_keywords` varchar(255) NOT NULL COMMENT '分类关键词，用,分割',
      `category_description` text NOT NULL COMMENT '分类描述',
      PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '分类板块表';

#----------------------
# 标签表
#----------------------
CREATE TABLE `tag_data`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `tag_id` bigint(20) UNSIGNED NOT NULL COMMENT '标签id',
      `tag_name` varchar(255) NOT NULL COMMENT '标签名称',
      PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '标签表';


#----------------------
# 推荐信息表
#----------------------
CREATE TABLE `recommend_data`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '操作者id',
      `recommend_id` int(8) UNSIGNED NOT NULL COMMENT '推荐id',
      `recommend_type` int(5) UNSIGNED NOT NULL COMMENT '推荐类型id，自己定义',
      `recommend_text` text COMMENT '推荐原因',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '推荐信息表';


#----------------------
# vip表
#----------------------
CREATE TABLE `vip_data`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `vip_status` tinyint(1) DEFAULT '0' COMMENT 'vip状态 0为正常',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT 'vip表';

#----------------------
# 评论表
#----------------------
CREATE TABLE `comments_data`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `postings_id` bigint(20) UNSIGNED NOT NULL COMMENT '帖子id',
      `comments_id` bigint(20) UNSIGNED NOT NULL COMMENT '评论id',
      `comments_chat_text` text COMMENT '评论内容',
      `comments_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
      UNIQUE KEY `comments_and_id` (`postings_id`,`comments_id`),
      PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '评论表';

#----------------------
# 帖子表
#----------------------
CREATE TABLE `postings_data`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `postings_id` bigint(20) UNSIGNED NOT NULL COMMENT '帖子id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '帖子发布的id',
      `postings_content` text NOT NULL COMMENT '帖子内容',
      `postings_file_id` bigint(20) NULL DEFAULT -1 COMMENT '是否有文件 如有文件就是文件id地址 没有就是-1',
      UNIQUE (`postings_id`),
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '帖子表';

#----------------------
# 帖子信息表
#----------------------
CREATE TABLE `postings_info`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `postings_id` bigint(20) UNSIGNED NOT NULL COMMENT '帖子id',
      `postings_title` varchar(255) not null comment '帖子标题',
      `postings_outline` varchar(255) NOT NULL COMMENT '帖子简介',
      `postings_tag` varchar(255) NOT NULL COMMENT '帖子标签，用,分割开关键词',
      `postings_tutorial` tinyint(1) NULL DEFAULT 0 COMMENT '默认0 是否是教程',
      `postings_money_id` int(11) NULL DEFAULT -1 COMMENT '默认0 是否有付费，如果有则为id，没有则为-1',
      `postings_vip_read` tinyint(1) NULL DEFAULT 0 COMMENT '默认0 是否vip才能访问',
      `postings_vip_category_id` int(11) NOT NULL COMMENT '分类id',
      UNIQUE (`postings_id`),
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '帖子信息表';

#----------------------
# 帖子其他信息
#----------------------
CREATE TABLE `postings_other`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `postings_id` bigint(20) UNSIGNED NOT NULL COMMENT '帖子id',
      `postings_likes` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '帖子点赞数',
      `postings_views` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '帖子浏览量数',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '帖子其他信息';

#----------------------
# 用户数据分析表
#----------------------
CREATE TABLE `user_analyse_data`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `weighted_user_tag` int(5) UNSIGNED NOT NULL COMMENT '用户访问的关键词id或自己自定义的关键词id,多个用，分割开',
      `weighted_user_category` int(5) UNSIGNED NOT NULL COMMENT '访问的板块或自己定义的板块id，多个用，分割开',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '帖子其他信息';

#----------------------
# 数据分析以及推荐权重表
#----------------------
CREATE TABLE `analyse_data`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `postings_id` bigint(20) UNSIGNED NOT NULL COMMENT '帖子id',
      `postings_weighted` int(5) UNSIGNED NOT NULL COMMENT '帖子权重',
      `weighted_tag` int(5) UNSIGNED NOT NULL COMMENT '帖子所属标签',
      `weighted_category` int(5) UNSIGNED NOT NULL COMMENT '帖子所属板块',
      `weighted_type` text NOT NULL COMMENT '推荐理由',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '数据分析以及推荐权重表';


#----------------------
# 充值流水表
#----------------------
CREATE TABLE `order_bill_recharge`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `bill_recharge_info` text NOT NULL COMMENT '充值信息',
      `bill_recharge_addr` text NOT NULL COMMENT '充值来源',
      `bill_recharge_data` text NOT NULL COMMENT '充值账单',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '充值流水表';

#----------------------
# 积分流水表
#----------------------
CREATE TABLE `order_bill_credits`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `bill_credits_data` text NOT NULL COMMENT '积分具体信息',
      `bill_credits_admin_id` int(11) NULL DEFAULT 0 COMMENT '管理员id,如果没有赠送事件就默认为-1',
      `bill_credits_admin_data` text NULL DEFAULT NULL COMMENT '给予原因,如果没有默认就为null',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '积分流水表';

#----------------------
# 虚拟币流水表
#----------------------
CREATE TABLE `order_bill_golds`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `bill_golds_data` text NOT NULL COMMENT '虚拟币具体信息',
      `bill_golds_admin_id` int(11) NULL DEFAULT 0 COMMENT '管理员id,如果没有赠送事件就默认为-1',
      `bill_golds_admin_data` text NULL DEFAULT NULL COMMENT '给予原因,如果没有默认就为null',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '虚拟币流水表';

#----------------------
# 令牌持久化表
#----------------------
CREATE TABLE `user_token`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `token_expired_time` timestamp NOT NULL COMMENT '过期时间',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '令牌持久化表';

#----------------------
# 临时数据表
#----------------------
CREATE TABLE `cache` (
      `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键Id',
       `cache_data` text NOT NULL COMMENT '临时数据',
      `cache_expired_time` timestamp NOT NULL COMMENT '过期时间',
       PRIMARY KEY(id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '临时数据表';

#----------------------
# 网站信息表
#----------------------
CREATE TABLE `website_data`(
     `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
     `name` text NOT NULL COMMENT '名称',
     `keyword` text COMMENT '关键词多个以,分隔开',
     `description` text COMMENT '描述',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '网站信息表';

