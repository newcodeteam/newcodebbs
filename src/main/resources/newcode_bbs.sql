CREATE DATABASE IF NOT EXISTS newcode_bbs CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';

USE newcode_bbs;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `newcodebbs_user`;
CREATE TABLE `new_code_bbs_user`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
    `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
    `user_mail` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮箱',
    `user_pwd` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `user_concern` int(3) UNSIGNED NULL DEFAULT 0 COMMENT '关注数量',
    `user_fans` int(8) UNSIGNED NULL DEFAULT 0 COMMENT '粉丝数量',
    `user_article` int(8) UNSIGNED NULL DEFAULT 0 COMMENT '文章数量',
    `user_comments` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '评论数量',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;