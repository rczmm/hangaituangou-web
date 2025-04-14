package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import com.dsy.hangaituangou.enums.InterviewStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("`interview_schedule`")
public class Interview extends BaseEntity {

    @TableField("user_id")
    private String userId;

    @TableField("job_name")
    private String jobName;

    @TableField("interview_round")
    private String round;

    @TableField("interview_type")
    private String type;

    @TableField("scheduled_time")
    private String scheduledTime;

    @TableField("duration_minutes")
    private String durationTime;

    @TableField("location")
    private String location;

    @EnumValue
    private InterviewStatusEnum status;

    private String hrNotes;

    @TableField("interview_info")
    private String interviewInfo;

    private String interviewLink;

    private String direction;

}
