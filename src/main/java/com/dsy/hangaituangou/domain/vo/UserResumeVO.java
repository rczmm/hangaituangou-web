package com.dsy.hangaituangou.domain.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Schema(description = "简历信息")
public class UserResumeVO {

    private String id;
    private String userId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String jobStatus;
    private String strengths;
    private String expectations;
    private List<Map<String, Object>> workExperiences;
    private List<Map<String, Object>> projectExperiences;
    private List<Map<String, Object>> educationExperiences;
    private List<String> honors;
    private List<String> certifications;
    private List<String> skills;
    private String personality;

}

