package com.example.DBS.controller;

import com.example.DBS.DTO.MotionFunctionDTO;
import com.example.DBS.domain.BaseResponseBody;
import com.example.DBS.domain.CustomResponseBody;
import com.example.DBS.domain.MotionFunction;
import com.example.DBS.service.MotionFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Motion,Function 관련 컨트롤러*
 */
@RestController
@RequiredArgsConstructor
public class MotionFunctionController {
    private final MotionFunctionService motionFunctionService;

    /**
     * Motion, Function 설정 저장 API
     * 모션, 설정을 받아서 DB에 저장
     * @param requestBody 모션 기능 설정
     * @return 성공 여부
     */
    @PostMapping("/motionfunction")
    public ResponseEntity<BaseResponseBody> saveMotionSetting(@RequestBody List<MotionFunctionDTO> requestBody){
        BaseResponseBody responseBody = new BaseResponseBody("모션 설정 저장 성공");
        List<MotionFunction> motionFunctionList = new ArrayList<>();
        try{
            motionFunctionService.saveMotionFunction(requestBody);
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
     * 유저별 Motion, Function 설정 조회 API
     * userId를 받아서 해당하는 모션 기능 설정 반환
     * @param userId 유저 아이디
     * @return 성공 여부, 모션 기능 설정
     */
    @GetMapping("/motionfunction")
    public ResponseEntity<CustomResponseBody<MotionFunctionDTO>> getMotionFunction(@RequestParam(name = "userId") Long userId){
        CustomResponseBody<MotionFunctionDTO> responseBody = new CustomResponseBody<>("");
        List<MotionFunctionDTO> list = null;
        try{
            list = motionFunctionService.getMotionFunctionByUser(userId);
            responseBody.setList(list);
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
