package com.dsy.hangaituangou.domain;

import com.dsy.hangaituangou.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserProfile extends BaseEntity {

    private String userId;
    private String exJob;
    private String exSalary;
    private String exMinSalary;
    private String exMaxSalary;
    private String personIntroduction;
    private String workExperience;
    private String name;
    private String specialty;
    private String city;

}
