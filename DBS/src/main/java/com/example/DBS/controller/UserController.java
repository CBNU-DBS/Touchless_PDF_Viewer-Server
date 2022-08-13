package com.example.DBS.controller;

import com.example.DBS.domain.BaseResponseBody;
import com.example.DBS.domain.CustomResponseBody;
import com.example.DBS.domain.User;
import com.example.DBS.service.MotionFunctionService;
import com.example.DBS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MotionFunctionService motionFunctionService;
    @PostMapping("/users")
    public ResponseEntity<BaseResponseBody> signup (@RequestBody User user){
        BaseResponseBody responseBody = new CustomResponseBody<>("회원가입 성공");
        try {
            Long userId = userService.signUp(user);
            motionFunctionService.setDefaultMotionSetting(userId);
        } catch (RuntimeException re){
            responseBody.setResultCode(-1);
            responseBody.setResultMsg(re.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        } catch (Exception e){
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }

        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/users/login")
    public ResponseEntity login (@RequestBody User user){
        CustomResponseBody<User> responseBody = new CustomResponseBody<>("로그인 성공");
        try {
            User loginUser = userService.login(user);
            responseBody.getList().add(loginUser);
        } catch (RuntimeException re){
            responseBody.setResultCode(-1);
            responseBody.setResultMsg(re.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        } catch (Exception e){
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
        return ResponseEntity.ok().body(responseBody);
    }
}
