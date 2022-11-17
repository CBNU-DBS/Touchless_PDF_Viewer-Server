package com.example.DBS.service;

import com.example.DBS.DTO.MotionFunctionDTO;
import com.example.DBS.domain.Func;
import com.example.DBS.domain.Motion;
import com.example.DBS.domain.MotionFunction;
import com.example.DBS.domain.User;
import com.example.DBS.repository.FuncRepository;
import com.example.DBS.repository.MotionFunctionRepository;
import com.example.DBS.repository.MotionRepository;
import com.example.DBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 모션 기능 관련 서비스 로직
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MotionFunctionService {
    private final MotionFunctionRepository motionFunctionRepository;
    private final UserRepository userRepository;
    private final MotionRepository motionRepository;
    private final FuncRepository funcRepository;

    /**
     * 모션 기능 설정 저장 서비스 로직
     * @param motionFunctionList 모션 기능 설정 정보
     */
    @Transactional
    public void saveMotionFunction(List<MotionFunctionDTO> motionFunctionList){
        Long userId = motionFunctionList.get(0).getUserId();
        User user = userRepository.findOne(userId);
        Motion motion = null;
        Func func = null;
        for(MotionFunctionDTO motionFunctionDTO: motionFunctionList){
            if(!motionFunctionDTO.getUserId().equals(userId)){
                throw new IllegalStateException("유저 ID가 잘못되었습니다.");
            }
            motion = motionRepository.findByName(motionFunctionDTO.getMotion());
            func = funcRepository.findByName(motionFunctionDTO.getFunction());
            if(motion == null || func == null){
                throw new IllegalStateException("모션, 기능 이름이 잘못되었습니다.");
            }
            MotionFunction motionFunction = new MotionFunction();
            motionFunction.setUser(user);
            motionFunction.setMotion(motion);
            motionFunction.setFunc(func);
            motionFunctionRepository.save(motionFunction);
        }
    }

    /**
     * userId로 모션 설정 조회하는 서비스 로직
     * @param userId 유저 아이디
     * @return 모션 기능 설정
     */
    public List<MotionFunctionDTO> getMotionFunctionByUser(Long userId){
        User user = userRepository.findOne(userId);
        List<MotionFunctionDTO> result = new ArrayList<>();
        if(user == null){
            throw new IllegalStateException("유저가 없습니다.");
        }
        if(user.getMotionFunctionList().isEmpty()){
            throw new IllegalStateException("저장된 모션 기능이 없습니다.");
        }
        for(MotionFunction mf : user.getMotionFunctionList()){
            result.add(new MotionFunctionDTO(mf.getUser().getId(),mf.getMotion().getName(),mf.getFunc().getName()));
        }
        return result;
    }
}
