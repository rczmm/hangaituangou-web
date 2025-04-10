package com.dsy.hangaituangou.domain.bo;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class UserResumeBO {
    private Long id;
    private Long userId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String jobSeekingStatus;
    private String personalStrengths;
    private String jobExpectations;
    private String personalityTraits;
    private List<Map<String, Object>> workExperience;
    private List<Map<String, Object>> projectExperience;
    private List<Map<String, Object>> educationHistory;
    private List<Map<String, Object>> honorsAwards;
    private List<Map<String, Object>> certificates;
    private List<Map<String, Object>> professionalSkills;
}