package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsy.hangaituangou.domain.base.BaseEntity; // 确认BaseEntity的路径
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 确认 BaseEntity 中 isDeleted 的类型，如果是 Integer，请将下面的 Boolean isDeleted 调整为 Integer

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户上传的简历文件信息实体")
@TableName("resume_files") // 映射到 resume_files 表
public class ResumeFile extends BaseEntity { // 继承 BaseEntity

    @Schema(description = "关联的用户ID (逻辑外键, 指向 sys_user.id)")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "原始文件名", required = true)
    @TableField("file_name")
    private String fileName;

    @Schema(description = "文件存储路径或URL", required = true)
    @TableField("file_path")
    private String filePath; // 存储相对路径或完整URL

    @Schema(description = "文件MIME类型 (e.g., application/pdf)")
    @TableField("file_type")
    private String fileType;

    @Schema(description = "文件大小 (单位: 字节)")
    @TableField("file_size")
    private Long fileSize; // 文件大小通常用 Long

    @Schema(description = "用户自定义简历标签/名称")
    @TableField("resume_label")
    private String resumeLabel;

    @Schema(description = "是否为用户的默认简历文件: false-否, true-是", defaultValue = "false", required = true)
    @TableField("is_default")
    private Boolean isDefault; // 映射 tinyint(1)

}
