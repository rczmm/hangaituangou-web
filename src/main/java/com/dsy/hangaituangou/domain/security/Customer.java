package com.dsy.hangaituangou.domain.security;

import com.dsy.hangaituangou.domain.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class Customer extends User {

    private final SysUser sysUser;

    public Customer(SysUser sysUser, Collection<? extends GrantedAuthority> authorities, SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
