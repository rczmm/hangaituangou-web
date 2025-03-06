package com.dsy.hangaituangou.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "登录请求参数")
public class LoginBo {

    @Schema(description = "用户名", example = "admin")
    @NotNull(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码", example = "123456")
    @NotNull(message = "密码不能为空")
    private String password;

}
