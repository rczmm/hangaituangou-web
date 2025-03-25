package com.dsy.hangaituangou.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "登录响应数据")
public class LoginVo {

    @Schema(description = "用户认证令牌", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "用户ID", example = "1")
    private Long userId;

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "昵称", example = "管理员")
    private String nickname;
    
    @Schema(description = "头像URL", example = "https://example.com/avatar.jpg")
    private String avatar;

}