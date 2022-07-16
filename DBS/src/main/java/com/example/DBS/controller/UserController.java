package com.example.DBS.controller;

import com.example.DBS.domain.User;
import com.example.DBS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<UserResponse> signup (@RequestBody User user){

    }
}
