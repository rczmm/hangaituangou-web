-- 更新数据库表结构，确保与实体类注解一致

-- 创建候选人表
CREATE TABLE IF NOT EXISTS `t_candidate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `candidate_id` varchar(50) NOT NULL COMMENT '候选人ID',
  `candidate_name` varchar(100) NOT NULL COMMENT '候选人姓名',
  `candidate_phone` varchar(20) DEFAULT NULL COMMENT '候选人电话',
  `candidate_email` varchar(100) DEFAULT NULL COMMENT '候选人邮箱',
  `candidate_gender` varchar(10) DEFAULT NULL COMMENT '候选人性别',
  `candidate_dob` datetime DEFAULT NULL COMMENT '候选人出生日期',
  `candidate_address` varchar(255) DEFAULT NULL COMMENT '候选人地址',
  `candidate_nationality` varchar(50) DEFAULT NULL COMMENT '候选人国籍',
  `candidate_education` varchar(50) DEFAULT NULL COMMENT '候选人学历',
  `candidate_major` varchar(100) DEFAULT NULL COMMENT '候选人专业',
  `candidate_experience` varchar(50) DEFAULT NULL COMMENT '工作经验',
  `candidate_skills` json DEFAULT NULL COMMENT '技能列表',
  `applied_jobs` json DEFAULT NULL COMMENT '应聘岗位列表',
  `resume_file` json DEFAULT NULL COMMENT '简历文件信息',
  `interview_status` varchar(50) DEFAULT NULL COMMENT '面试状态',
  `interview_schedule` json DEFAULT NULL COMMENT '面试安排',
  `interview_feedback` text DEFAULT NULL COMMENT '面试反馈',
  `hiring_status` varchar(50) DEFAULT NULL COMMENT '招聘状态',
  `remarks` text DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_candidate_id` (`candidate_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='候选人表';

-- 创建岗位表
CREATE TABLE IF NOT EXISTS `t_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `job_title` varchar(100) NOT NULL COMMENT '职位标题',
  `job_type` varchar(50) DEFAULT NULL COMMENT '工作类型',
  `job_description` text DEFAULT NULL COMMENT '职位描述',
  `recruitment_count` int(11) DEFAULT NULL COMMENT '招聘人数',
  `work_location` varchar(255) DEFAULT NULL COMMENT '工作地点',
  `salary_range` varchar(100) DEFAULT NULL COMMENT '薪资范围',
  `benefits` json DEFAULT NULL COMMENT '福利待遇',
  `education_requirements` varchar(100) DEFAULT NULL COMMENT '学历要求',
  `experience_requirements` varchar(255) DEFAULT NULL COMMENT '经验要求',
  `skills_requirements` json DEFAULT NULL COMMENT '技能要求',
  `language_requirements` varchar(255) DEFAULT NULL COMMENT '语言要求',
  `other_requirements` text DEFAULT NULL COMMENT '其他要求',
  `job_status` varchar(50) DEFAULT NULL COMMENT '职位状态',
  `post_date` datetime DEFAULT NULL COMMENT '发布日期',
  `close_date` datetime DEFAULT NULL COMMENT '截止日期',
  `department` varchar(100) DEFAULT NULL COMMENT '部门',
  `recruitment_source` varchar(100) DEFAULT NULL COMMENT '招聘来源',
  `job_tags` json DEFAULT NULL COMMENT '职位标签',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `publisher_id` bigint(20) DEFAULT NULL COMMENT '发布者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_publisher_id` (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位表';

-- 创建通知表
CREATE TABLE IF NOT EXISTS `t_notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) NOT NULL COMMENT '通知标题',
  `content` text DEFAULT NULL COMMENT '通知内容',
  `last_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `color` varchar(20) DEFAULT NULL COMMENT '通知颜色',
  `type` varchar(50) DEFAULT NULL COMMENT '通知类型',
  `status` tinyint(1) DEFAULT 0 COMMENT '通知状态：0-未读，1-已读',
  `priority` tinyint(1) DEFAULT 0 COMMENT '优先级：0-普通，1-重要，2-紧急',
  `is_top` tinyint(1) DEFAULT 0 COMMENT '是否置顶：0-否，1-是',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `sender_id` bigint(20) DEFAULT NULL COMMENT '发送者ID',
  `receiver_id` bigint(20) DEFAULT NULL COMMENT '接收者ID',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_receiver_id` (`receiver_id`),
  KEY `idx_sender_id` (`sender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知表';

-- 创建系统角色表
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `code` varchar(50) NOT NULL COMMENT '角色编码',
  `status` tinyint(1) DEFAULT 0 COMMENT '角色状态：0-正常，1-禁用',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '所属租户ID',
  `is_system` tinyint(1) DEFAULT 0 COMMENT '是否系统内置角色：0-否，1-是',
  `sort` int(11) DEFAULT 0 COMMENT '排序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';

-- 创建系统租户表
CREATE TABLE IF NOT EXISTS `sys_tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '租户名称',
  `code` varchar(50) NOT NULL COMMENT '租户唯一编码',
  `status` tinyint(1) DEFAULT 0 COMMENT '租户状态：0-正常，1-禁用',
  `type` tinyint(1) DEFAULT 0 COMMENT '租户类型：0-企业，1-个人',
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `contact_email` varchar(100) DEFAULT NULL COMMENT '联系邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '企业地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统租户表';

-- 创建系统用户表
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `status` tinyint(1) DEFAULT 0 COMMENT '用户状态：0-正常，1-禁用，2-未激活',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐值，用于密码加密',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '所属租户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '用户角色ID',
  `user_type` tinyint(1) DEFAULT 0 COMMENT '用户类型：0-HR，1-求职者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- 创建用户角色关联表
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_role` (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';