package com.dsy.hangaituangou.domain.bo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginBo {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;


}
