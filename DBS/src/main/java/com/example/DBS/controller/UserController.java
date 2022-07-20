package com.example.DBS.controller;

import com.example.DBS.domain.CustomResponseBody;
import com.example.DBS.domain.User;
import com.example.DBS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<CustomResponseBody<User>> signup (@RequestBody User user){
        CustomResponseBody<User> responseBody = new CustomResponseBody<>("회원가입 성공");
        try {
            userService.signUp(user);

        } catch (RuntimeException re){
            responseBody.setResultCode(-1);
            responseBody.setResultMsg("런타임 에러");
            return ResponseEntity.badRequest().body(responseBody);
        } catch (Exception e){
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }

        return ResponseEntity.ok().body(responseBody);
    }
}
