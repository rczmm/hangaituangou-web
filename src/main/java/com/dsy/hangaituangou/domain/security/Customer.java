package com.dsy.hangaituangou.domain.security;

import com.dsy.hangaituangou.domain.SysUser;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class Customer extends User {

    private SysUser sysUser;

    public Customer(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUsername(), sysUser.getPassword(), authorities);
        this.sysUser = sysUser;
    }
}
