package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "简历文件信息实体")
@TableName("t_resume_file")
public class ResumeFileEntity extends BaseEntity {
    @Schema(description = "候选人ID", example = "C001")
    @TableField("candidate_id")
    private String candidateId;
    
    @Schema(description = "文件名", example = "张三_Java开发工程师_简历.pdf")
    @TableField("file_name")
    private String fileName;
    
    @Schema(description = "文件类型", example = "application/pdf")
    @TableField("file_type")
    private String fileType;
    
    @Schema(description = "文件大小(字节)", example = "512000")
    @TableField("file_size")
    private Long fileSize;
    
    @Schema(description = "文件路径", example = "/resumes/张三_Java开发工程师_简历.pdf")
    @TableField("file_path")
    private String filePath;
    
    @Schema(description = "上传日期", example = "2025-02-10")
    @TableField("upload_date")
    private Date uploadDate;
    
    @Schema(description = "租户ID")
    @TableField("tenant_id")
    private Long tenantId;
}