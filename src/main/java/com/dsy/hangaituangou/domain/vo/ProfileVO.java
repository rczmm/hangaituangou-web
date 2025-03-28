package com.dsy.hangaituangou.domain.vo;


import lombok.Data;

import java.util.List;

@Data
public class ProfileVO {

    private String id;

    private String name;

    private String avatarUrl;

    private String email;

    private String phone;

    private String accountStatus;

    private List<String> stats;

}
