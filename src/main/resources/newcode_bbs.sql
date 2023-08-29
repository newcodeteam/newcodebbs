CREATE DATABASE IF NOT EXISTS newcode_bbs CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';

USE newcode_bbs;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `user_data`;
DROP TABLE IF EXISTS `user_type`;
DROP TABLE IF EXISTS `user_fans_one`;
DROP TABLE IF EXISTS `user_fans_two`;
DROP TABLE IF EXISTS `user_chat_one`;
DROP TABLE IF EXISTS `user_chat_two`;
DROP TABLE IF EXISTS `user_file`;
DROP TABLE IF EXISTS `user_type_group`;


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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;


#----------------------
# 权限表
#----------------------
CREATE TABLE `user_type`  (
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
      `user_type_nickname` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '头衔昵称,多个昵称以,隔开',
      `user_type_id` int(3) UNSIGNED NULL DEFAULT 0 COMMENT '权限id,默认0 游客组 无权限',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

#----------------------
# 权限表 0 为空 1 代表有权限
#----------------------
CREATE TABLE `user_type_group`  (
      `user_type_id` int(6) UNSIGNED NOT NULL COMMENT '权限Id',
      `user_type_name` char(20) NOT NULL default '' COMMENT '权限名称',
      `user_type_all` int(11) NULL default 0 COMMENT '任何权限',
      `user_type_credits_from` int(11) NULL default 0 COMMENT '编辑积分权限',
      `user_type_allow_read` int(11) NULL default 0 COMMENT '允许访问权限',
      `user_type_allow_article` int(11) NULL default 0 COMMENT '允许发帖子权限',
      `user_type_allow_comments` int(11) NULL default 0 COMMENT '允许评论权限',
      `user_type_allow_attach` int(11) NULL default 0 COMMENT '允许上传文件权限',
      `user_type_allow_down` int(11) NULL default 0 COMMENT '允许下载文件权限',
      `user_type_allow_top` int(11) NULL default 0 COMMENT '允许顶置帖子权限',
      `user_type_allow_update` int(11) NULL default 0 COMMENT '允许更改帖子权限',
      `user_type_allow_delete` int(11) NULL default 0 COMMENT '允许删除帖子权限',
      `user_type_allow_move` int(11) NULL default 0 COMMENT '允许移动帖子板块权限',
      `user_type_allow_ban_user` int(11) NULL default 0 COMMENT '允许禁止用户权限',
      `user_type_allow_delete_user` int(11) NULL default 0 COMMENT '允许删除用户权限',
      `user_type_allow_view_ip` int(11) unsigned NULL default 0 COMMENT '允许查看用户敏感信息权限',
      PRIMARY KEY (`user_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

INSERT INTO `user_type_group` SET user_type_id='0', user_type_name='游客组', user_type_all=0, user_type_credits_from=0, user_type_allow_read=1, user_type_allow_article=0, user_type_allow_comments=0, user_type_allow_attach=0, user_type_allow_down=0, user_type_allow_top=0, user_type_allow_update=0, user_type_allow_delete=0, user_type_allow_move=0, user_type_allow_ban_user=0, user_type_allow_delete_user=0, user_type_allow_view_ip=0;
INSERT INTO `user_type_group` SET user_type_id='1', user_type_name='管理员组', user_type_all=1;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

#----------------------
# 关注与粉丝表 > 5000 id
#----------------------
CREATE TABLE `user_fans_two`  (
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
      `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '粉丝id',
      `user_fans_followed_id` bigint(20) UNSIGNED NOT NULL COMMENT '关注的用户id',
      `user_fans_status` tinyint(1) DEFAULT '0' COMMENT '关注状态(0关注 1取消)',
      PRIMARY KEY (`id`) USING BTREE,
      UNIQUE KEY `user_followed_indx` (`user_id`,`user_fans_followed_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

#----------------------
# 文件和图片表
#----------------------
CREATE TABLE `user_file`(
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `user_file_addr` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 0 COMMENT '文件或者图片地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

