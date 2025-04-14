package com.dsy.hangaituangou.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileAppVO {
    private String id;
    private Long userId;
    private String exJob;
    private String exMinSalary;
    private String exMaxSalary;
    private String personIntroduction;
    private String workExperience;
    private String name;
    private List<String> specialty;
    private String city;
}
