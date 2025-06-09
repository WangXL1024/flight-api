package com.example.flightapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.flightapi.dto.UserRegisterRequest;
import com.example.flightapi.dto.UserResponse;
import com.example.flightapi.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/user")
public class UserController {

    // private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegisterRequest request) {   
        // 注册逻辑
        userService.registerUser(request);
        return ResponseEntity.ok().build();
    }

}
