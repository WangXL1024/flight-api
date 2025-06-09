package com.example.flightapi.service;

import com.example.flightapi.dto.UserRegisterRequest;
import com.example.flightapi.dto.UserResponse;
import com.example.flightapi.entity.User;
import com.example.flightapi.repository.UserRepository;
import com.example.flightapi.utils.EmailExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponse registerUser(UserRegisterRequest request) {
        // 检查邮箱是否已注册
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailExistsException("邮箱已被注册: " + request.getEmail());
        }

        // 创建新用户
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setCountry(request.getCountry());
        user.setPhone(request.getPhone());

        // 保存用户到数据库
        User savedUser = userRepository.save(user);

        // 返回用户响应
        return new UserResponse(
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getCountry(),
                savedUser.getPhone(),
                savedUser.getCreatedAt()
        );
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}  
