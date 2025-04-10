package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_resume")
public class UserResume extends BaseEntity {
    
    private Long userId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String jobSeekingStatus;
    private String personalStrengths;
    private String jobExpectations;
    private String personalityTraits;
    private String workExperience;
    private String projectExperience;
    private String educationHistory;
    private String honorsAwards;
    private String certificates;
    private String professionalSkills;

}