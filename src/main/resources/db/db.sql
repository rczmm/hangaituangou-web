/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : hangaituangou

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 14/04/2025 22:50:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告内容 (可以使用HTML或Markdown)',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'GENERAL' COMMENT '公告类型 (例如: SYSTEM_UPDATE: 系统更新, PROMOTION: 优惠活动, MAINTENANCE: 维护通知, GENERAL: 一般通知)',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'DRAFT' COMMENT '公告状态 (例如: DRAFT: 草稿, PUBLISHED: 已发布, ARCHIVED: 已归档)',
  `priority` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '优先级 (数值越大优先级越高, 可用于排序)',
  `publish_time` timestamp NULL DEFAULT NULL COMMENT '发布时间 (公告实际开始对外展示的时间)',
  `expiry_time` timestamp NULL DEFAULT NULL COMMENT '过期时间 (公告自动下线的时间, NULL表示永不过期)',
  `publisher_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '发布者用户ID (逻辑外键, 关联用户表, NULL可能表示系统自动发布)',
  `publisher_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布者名称 (冗余字段, 方便展示)',
  `target_audience` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'ALL' COMMENT '目标受众 (例如: ALL: 所有用户, SPECIFIC_GROUP: 特定用户组, 等)',
  `is_deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识 (0: 未删除, 1: 已删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status_publish_expiry`(`status` ASC, `publish_time` ASC, `expiry_time` ASC) USING BTREE COMMENT '用于查询有效公告的状态、发布和过期时间索引',
  INDEX `idx_type`(`type` ASC) USING BTREE COMMENT '公告类型索引',
  INDEX `idx_priority`(`priority` ASC) USING BTREE COMMENT '优先级索引',
  INDEX `idx_publisher_id`(`publisher_id` ASC) USING BTREE COMMENT '发布者ID索引',
  INDEX `idx_is_deleted`(`is_deleted` ASC) USING BTREE COMMENT '逻辑删除索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for chat_message
-- ----------------------------
DROP TABLE IF EXISTS `chat_message`;
CREATE TABLE `chat_message`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息ID，自增主键',
  `conversation_id` bigint UNSIGNED NOT NULL COMMENT '会话ID，标识消息所属的会话',
  `sender_id` bigint UNSIGNED NOT NULL COMMENT '发送者ID，标识发送消息的用户',
  `recipient_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '接收者ID，如果是一对一聊天则标识接收消息的用户，群聊时可以为空或使用其他表关联',
  `message_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'text' COMMENT '消息类型，例如：text（文本）、image（图片）、video（视频）、audio（音频）、file（文件）等',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息内容',
  `is_deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除，0-否，1-是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，记录消息在数据库中创建的时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，记录消息最后一次更新的时间',
  `send_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间，记录消息发送时的精确时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'sent' COMMENT '消息状态，例如：sent（已发送）、delivered（已送达）、read（已读）、failed（发送失败）等',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_conversation_id`(`conversation_id` ASC) USING BTREE COMMENT '会话ID索引，用于快速查询某个会话的所有消息',
  INDEX `idx_sender_id`(`sender_id` ASC) USING BTREE COMMENT '发送者ID索引，用于查询某个用户发送的所有消息',
  INDEX `idx_recipient_id`(`recipient_id` ASC) USING BTREE COMMENT '接收者ID索引，用于查询发送给某个用户的消息（一对一聊天）',
  INDEX `idx_sent_at`(`send_at` ASC) USING BTREE COMMENT '发送时间索引，用于按时间顺序查询消息'
) ENGINE = InnoDB AUTO_INCREMENT = 122 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '聊天消息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for common_phrases
-- ----------------------------
DROP TABLE IF EXISTS `common_phrases`;
CREATE TABLE `common_phrases`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID, 对应 @TableId(value = \"id\", type = IdType.AUTO)',
  `user_id` bigint NOT NULL COMMENT '用户ID, 存储常用语所属的用户',
  `phrase_text` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '常用语内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间, 对应 @TableField(value = \"create_time\", fill = FieldFill.INSERT)',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间, 对应 @TableField(value = \"update_time\", fill = FieldFill.INSERT_UPDATE)',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标志 (0: 未删除, 1: 已删除), 对应 @TableField(value = \"is_deleted\") @TableLogic',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE COMMENT '用户ID索引，方便按用户查询常用语'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '常用语表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for companies
-- ----------------------------
DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID，无符号整数，自增长',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司名称，不能为空',
  `scale` enum('1-50人','51-200人','201-1000人','1001-5000人','5000人以上','未公开') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公司规模，使用枚举类型限定可选值，允许为空',
  `logo_url` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公司Logo图片的URL地址，允许为空，长度设为2048以支持较长的URL',
  `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公司官方网站URL，允许为空',
  `industry` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属行业，允许为空',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '公司简介或描述，允许为空，使用TEXT类型以存储较长文本',
  `established_date` date NULL DEFAULT NULL COMMENT '公司成立日期，允许为空',
  `is_verified` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否经过认证 (0: 未认证, 1: 已认证)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认为当前时间戳，对应 Java 中的 createTime 字段',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间，默认为当前时间戳，并在更新时自动更新，对应 Java 中的 updateTime 字段',
  `is_deleted` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE COMMENT '设置主键为id字段',
  UNIQUE INDEX `uk_company_name`(`name` ASC) USING BTREE COMMENT '为公司名称添加唯一索引，确保公司名称不重复'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公司信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for conversations
-- ----------------------------
DROP TABLE IF EXISTS `conversations`;
CREATE TABLE `conversations`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '会话ID，自增主键',
  `conversation_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'private' COMMENT '会话类型，例如：private（私聊）、group（群聊）',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会话名称，用于群聊，私聊时可以为空',
  `is_deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除，0-否，1-是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，记录会话创建的时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，记录会话最后一次更新的时间',
  `last_message_at` timestamp NULL DEFAULT NULL COMMENT '最后一条消息的发送时间',
  `last_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '最后一条消息',
  `sender_id` int NULL DEFAULT NULL COMMENT '发送者ID',
  `recipient_id` int NULL DEFAULT NULL COMMENT '接受者ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_id`(`sender_id` ASC, `recipient_id` ASC) USING BTREE COMMENT '唯一性约束',
  INDEX `idx_conversation_type`(`conversation_type` ASC) USING BTREE COMMENT '会话类型索引',
  INDEX `idx_last_message_at`(`last_message_at` ASC) USING BTREE COMMENT '最后一条消息发送时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '聊天会话表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for interview_schedule
-- ----------------------------
DROP TABLE IF EXISTS `interview_schedule`;
CREATE TABLE `interview_schedule`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `application_status_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联的用户职位申请状态记录ID (逻辑外键, 关联user_job_application_status表)',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID (逻辑外键, 冗余自申请状态表, 便于查询)',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '职位ID (逻辑外键, 冗余自申请状态表, 便于查询)',
  `interview_round` int UNSIGNED NULL DEFAULT 1 COMMENT '面试轮次 (例如: 1, 2, 3)',
  `interview_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '面试类型 (例如: 电话面试, 技术一面, HR面试, 综合面试, Panel面试)',
  `scheduled_time` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '计划面试时间',
  `duration_minutes` int UNSIGNED NULL DEFAULT NULL COMMENT '预计持续时长 (分钟)',
  `location` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '面试地点 (物理地址或在线会议链接)',
  `interview_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '面试官信息 (JSON数组, 例如: [{\"name\": \"张三\", \"email\": \"zhangsan@example.com\", \"title\": \"技术经理\"}, ...])',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'SCHEDULED' COMMENT '面试状态 (例如: SCHEDULED: 已安排, COMPLETED: 已完成, CANCELED: 已取消, RESCHEDULED: 已改期, NO_SHOW: 候选人未到场)',
  `feedback_summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '面试反馈摘要 (面试官或HR填写)',
  `candidate_notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '候选人准备笔记或面试后感想',
  `hr_notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT 'HR备注 (例如: 协调细节, 后续安排等)',
  `is_deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识 (0: 未删除, 1: 已删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  `interview_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '线上面试的链接',
  `direction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交通指引',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_application_status_id`(`application_status_id` ASC) USING BTREE COMMENT '申请状态ID索引',
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE COMMENT '用户ID索引',
  INDEX `idx_job_id`(`job_name` ASC) USING BTREE COMMENT '职位ID索引',
  INDEX `idx_scheduled_time`(`scheduled_time` ASC) USING BTREE COMMENT '面试时间索引，便于按时间查询',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '面试状态索引',
  INDEX `idx_is_deleted`(`is_deleted` ASC) USING BTREE COMMENT '逻辑删除索引'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '面试安排表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '职位标题',
  `salary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '薪资范围',
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司名称',
  `company_size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司规模',
  `company_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司Logo',
  `tags` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '职位标签 (JSON 格式)',
  `hr_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'HR 姓名',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工作地点',
  `work_experience` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工作经验要求',
  `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学历要求',
  `benefits` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '福利待遇 (JSON 格式)',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '职位描述',
  `requirements` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '职位要求 (JSON 格式)',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '职位状态',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发布日期',
  `interview_time` datetime NULL DEFAULT NULL COMMENT '面试时间',
  `is_favorite` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否收藏',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已删除 (0: 未删除, 1: 已删除)',
  `recruitment_count` int NOT NULL DEFAULT 1 COMMENT '招聘人数',
  `min_salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低薪资',
  `max_salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高薪资',
  `hr_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'HR id',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for resume_files
-- ----------------------------
DROP TABLE IF EXISTS `resume_files`;
CREATE TABLE `resume_files`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件简历主键ID',
  `user_id` bigint NOT NULL COMMENT '关联的用户ID (逻辑外键, 指向 sys_user.id)',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '原始文件名',
  `file_path` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件存储路径或URL (相对于配置的基路径或完整URL)',
  `file_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件MIME类型 (e.g., application/pdf)',
  `file_size` bigint NULL DEFAULT NULL COMMENT '文件大小 (单位: 字节)',
  `resume_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户自定义简历标签/名称 (可选)',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为用户的默认简历文件: 0-否, 1-是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间 (文件记录创建时间)',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标志: 0-未删除, 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_rf_user_id`(`user_id` ASC) USING BTREE COMMENT '用户ID索引 (用于查询用户的所有简历文件)'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户上传的简历文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT 0,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_login_time` datetime NULL DEFAULT NULL,
  `last_login_ip` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int NULL DEFAULT 0,
  `user_type` int NULL DEFAULT NULL,
  `role_type` int NULL DEFAULT NULL,
  `company_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '公司ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123465 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user_job_application_status
-- ----------------------------
DROP TABLE IF EXISTS `user_job_application_status`;
CREATE TABLE `user_job_application_status`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID (逻辑外键, 关联用户表)',
  `job_id` bigint UNSIGNED NOT NULL COMMENT '职位ID (逻辑外键, 关联职位表)',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态 (例如: SAVED: 已收藏, APPLIED: 已投递, COMMUNICATED: 已沟通, INTERVIEW_SCHEDULED: 待面试, OFFERED: 已发Offer, REJECTED: 不合适, WITHDRAWN: 已撤销)',
  `application_time` timestamp NULL DEFAULT NULL COMMENT '投递时间 (当status为APPLIED或之后的状态时记录)',
  `last_communication_time` timestamp NULL DEFAULT NULL COMMENT '最后沟通时间 (当status为COMMUNICATED或之后的状态时记录)',
  `interview_time` timestamp NULL DEFAULT NULL COMMENT '计划面试时间 (当status为INTERVIEW_SCHEDULED时记录)',
  `notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '用户备注 (例如: 面试轮次, HR联系方式等)',
  `is_deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识 (0: 未删除, 1: 已删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间 (通常表示首次互动或收藏时间)',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间 (状态变更时间)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_job`(`user_id` ASC, `job_id` ASC, `is_deleted` ASC) USING BTREE COMMENT '用户和职位的唯一组合索引 (在未删除记录中唯一)',
  INDEX `idx_user_id_status`(`user_id` ASC, `status` ASC) USING BTREE COMMENT '用户ID和状态组合索引，便于查询特定用户的各类申请',
  INDEX `idx_job_id`(`job_id` ASC) USING BTREE COMMENT '职位ID索引',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '状态索引，便于查询特定状态的所有申请',
  INDEX `idx_is_deleted`(`is_deleted` ASC) USING BTREE COMMENT '逻辑删除索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户职位申请与追踪状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT 0,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  `ex_job` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '期望职位',
  `ex_salary` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '期望薪资',
  `ex_min_salary` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '期望最低薪资',
  `ex_max_salary` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '期望最高薪资',
  `person_introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人介绍',
  `work_experience` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工作经验',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `specialty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人专长',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '当前城市',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_resume
-- ----------------------------
DROP TABLE IF EXISTS `user_resume`;
CREATE TABLE `user_resume`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '关联的用户ID (逻辑外键)',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系地址',
  `job_seeking_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '求职状态 (例如: 积极求职, 观望机会, 暂不考虑)',
  `personal_strengths` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '个人优势/自我评价',
  `job_expectations` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '求职期望 (期望职位, 行业, 城市, 薪资等)',
  `personality_traits` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '职业性格/性格特点',
  `work_experience` json NULL COMMENT '工作经历列表 (JSON数组, 例如: [{\"company\": \"公司A\", \"title\": \"职位\", \"start_date\": \"YYYY-MM\", \"end_date\": \"YYYY-MM/至今\", \"description\": \"工作描述\"}, ...])',
  `project_experience` json NULL COMMENT '项目经历列表 (JSON数组, 例如: [{\"project_name\": \"项目A\", \"role\": \"角色\", \"start_date\": \"YYYY-MM\", \"end_date\": \"YYYY-MM\", \"description\": \"项目描述\", \"link\": \"项目链接\"}, ...])',
  `education_history` json NULL COMMENT '教育经历列表 (JSON数组, 例如: [{\"school\": \"学校名称\", \"degree\": \"学位\", \"major\": \"专业\", \"start_date\": \"YYYY-MM\", \"end_date\": \"YYYY-MM\"}, ...])',
  `honors_awards` json NULL COMMENT '所获荣誉列表 (JSON数组, 例如: [{\"award_name\": \"奖项名称\", \"issuing_organization\": \"颁发机构\", \"date_received\": \"YYYY-MM\"}, ...])',
  `certificates` json NULL COMMENT '资格证书列表 (JSON数组, 例如: [{\"certificate_name\": \"证书名称\", \"issuing_body\": \"颁发机构\", \"issue_date\": \"YYYY-MM\", \"expiry_date\": \"YYYY-MM/长期\"}, ...])',
  `professional_skills` json NULL COMMENT '专业技能列表 (JSON数组, 例如: [{\"skill_name\": \"技能名称\", \"proficiency\": \"掌握程度\", \"duration\": \"使用时长\"}, ...] 或简单的 [\"技能1\", \"技能2\", ...])',
  `is_deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标识 (0: 未删除, 1: 已删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE COMMENT '用户ID索引，便于按用户查询',
  INDEX `idx_is_deleted`(`is_deleted` ASC) USING BTREE COMMENT '逻辑删除索引，便于查询有效记录'
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户个人简历表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
