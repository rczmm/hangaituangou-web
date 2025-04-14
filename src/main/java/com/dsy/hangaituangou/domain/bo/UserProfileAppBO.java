package com.dsy.hangaituangou.domain.bo;

import lombok.Data;

import java.util.List;

@Data
public class UserProfileAppBO {

    private String id;
    private String userId;
    private String name;
    private String exJob;
    private String exMinSalary;
    private String exMaxSalary;
    private String personIntroduction;
    private String workExperience;
    private List<String> specialty;
    private String city;
    private String avatarUrl;

}
