package com.dsy.hangaituangou.controller;

import com.dsy.hangaituangou.domain.bo.LoginBo;
import com.dsy.hangaituangou.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SysUserService sysUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginBo loginBo) {
        return sysUserService.login(loginBo);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Boolean logout() {
        return sysUserService.logout();
    }


}
