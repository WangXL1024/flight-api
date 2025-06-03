package com.example.flightapi.service;

import com.example.flightapi.dto.LoginUserDetails;
import com.example.flightapi.entity.User;
import com.example.flightapi.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        );

        return new LoginUserDetails(
                user.getUserId(),
                user.getEmail(),//因为是使用邮箱登录系统，所以邮箱作为UserDetails实例的用户名
                user.getFirstName()+" "+user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                authorities, // 自定义方法，将角色转换为 GrantedAuthority
                true
        );
        // //直接使用Spring的UserDetails实现类，以后可自定义实现一个UserDetails类返回
        // return new org.springframework.security.core.userdetails.User(
        //         user.getEmail(),
        //         user.getPassword(),
        //         authorities
        // );
    }
}    
