package com.dsy.hangaituangou.controller;

import com.dsy.hangaituangou.domain.bo.LoginBo;
import com.dsy.hangaituangou.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "认证管理", description = "用户登录、注册、登出相关接口")
public class AuthController {

    private final SysUserService sysUserService;

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Operation(summary = "用户登录", description = "用户通过用户名和密码登录系统")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "登录成功"),
        @ApiResponse(responseCode = "400", description = "用户名或密码错误")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginBo loginBo) {
        return sysUserService.login(loginBo);
    }

    @Operation(summary = "用户登出", description = "用户退出登录状态")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "登出成功")
    })
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        logoutHandler.logout(request, response, authentication);
        return "redirect:/home";
    }

    @Operation(summary = "用户注册", description = "新用户注册账号")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "注册成功"),
        @ApiResponse(responseCode = "400", description = "用户名已存在或注册失败")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Validated @RequestBody LoginBo loginBo) {
        return sysUserService.register(loginBo);
    }


}
