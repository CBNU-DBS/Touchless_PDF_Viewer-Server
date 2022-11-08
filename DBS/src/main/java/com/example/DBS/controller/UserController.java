package com.example.DBS.controller;

import com.example.DBS.DTO.LoginResultDTO;
import com.example.DBS.DTO.MotionFunctionDTO;
import com.example.DBS.domain.BaseResponseBody;
import com.example.DBS.domain.CustomResponseBody;
import com.example.DBS.domain.MotionFunction;
import com.example.DBS.domain.User;
import com.example.DBS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User 관련 컨트롤러*
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * User 회원가입 API*
     * @param user
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<BaseResponseBody> signup (@RequestBody User user){
        BaseResponseBody responseBody = new CustomResponseBody<>("회원가입 성공");
        try {
            Long userId = userService.signUp(user);
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

    /**
     * 로그인 API
     * @param user
     * @return
     */
    @PostMapping("/users/login")
    public ResponseEntity login (@RequestBody User user){
        CustomResponseBody<LoginResultDTO> responseBody = new CustomResponseBody<>("로그인 성공");
        LoginResultDTO loginResultDTO = new LoginResultDTO();
        try {
            User loginUser = userService.login(user);
            loginResultDTO.setId(loginUser.getId());
            loginResultDTO.setName(loginUser.getName());
            loginResultDTO.setEmail(loginUser.getEmail());
            loginResultDTO.setPassword(loginUser.getPassword());
            loginResultDTO.setPhone(loginUser.getPhone());
            for (MotionFunction motionFunction: loginUser.getMotionFunctionList()) {
                loginResultDTO.getMotionFunctionDTOList().add(new MotionFunctionDTO(loginUser.getId(), motionFunction.getMotion().getName(), motionFunction.getFunc().getName()));
            }
            responseBody.getList().add(loginResultDTO);
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

    /**
     * 비밀번호 변경 API
     * @param user
     * @return
     */
    @PostMapping("/users/changepw")
    public ResponseEntity changepw (@RequestBody User user){
        CustomResponseBody<User> responseBody = new CustomResponseBody<>("비밀번호 변경 성공");
        try {
            userService.changepassword(user);
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
