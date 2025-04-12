package com.dsy.hangaituangou.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileVO {

    private Long id;
    private String name;
    private String title;
    private List<String> skills;
    private String location;
    private String experience;
    private String expectedSalary;
    private String avatar;
    private String introduction;
    private String isHovered;
    private String isBlurred;
    private String category;
}
