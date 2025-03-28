package com.dsy.hangaituangou.controller;

import com.dsy.hangaituangou.domain.vo.ProfileVO;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户相关接口")
public class UserController {

    private final SysUserService sysUserService;

    @Operation(summary = "个人资料", description = "个人资料")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "400", description = "用户名或密码错误")
    })
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public RespBase<ProfileVO> profile() {
        return RespBase.success(sysUserService.profile());
    }

}
