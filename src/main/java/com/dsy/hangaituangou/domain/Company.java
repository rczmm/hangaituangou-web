package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date; // 使用 java.util.Date 对应 TIMESTAMP 或 DATE

/**
 * <p>
 * 公司信息表 (无物理外键)
 * </p>
 *
 * @author YourName (或者生成器名称)
 * @since YYYY-MM-DD (生成日期)
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("companies")
public class Company extends BaseEntity {


    /**
     * 公司名称，不能为空
     */
    @TableField("name")
    private String name;

    /**
     * 公司规模，使用枚举类型限定可选值，允许为空
     * 注意：数据库是 ENUM，Java 通常映射为 String 或自定义 Enum。这里用 String 方便通用。
     */
    @TableField("scale")
    private String scale;

    /**
     * 公司Logo图片的URL地址，允许为空，长度设为2048以支持较长的URL
     */
    @TableField("logo_url")
    private String logoUrl;

    /**
     * 公司官方网站URL，允许为空
     */
    @TableField("website")
    private String website;

    /**
     * 所属行业，允许为空
     */
    @TableField("industry")
    private String industry;

    /**
     * 公司简介或描述，允许为空，使用TEXT类型以存储较长文本
     */
    @TableField("description")
    private String description;

    /**
     * 公司成立日期，允许为空 (数据库 DATE 类型)
     */
    @TableField("established_date")
    private Date establishedDate;

    /**
     * 是否经过认证 (0: 未认证, 1: 已认证) (数据库 TINYINT(1) UNSIGNED)
     */
    @TableField("is_verified")
    private Boolean isVerified;
}
