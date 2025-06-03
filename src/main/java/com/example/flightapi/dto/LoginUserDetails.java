package com.example.flightapi.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUserDetails implements UserDetails {
    
    private final Long id;
    private final String username;
    private final String fullName;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public LoginUserDetails(
            Long id,
            String username,
            String fullName,
            String password,
            String email,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled
    ) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    // UserDetails 接口方法实现
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // 自定义 getter 方法，用于获取额外信息
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName; // 返回全名
    }
}
