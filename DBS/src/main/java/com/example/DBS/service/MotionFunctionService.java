package com.example.DBS.service;

import com.example.DBS.DTO.MotionFunctionDTO;
import com.example.DBS.domain.Function;
import com.example.DBS.domain.Motion;
import com.example.DBS.domain.MotionFunction;
import com.example.DBS.domain.User;
import com.example.DBS.repository.FunctionRepository;
import com.example.DBS.repository.MotionFunctionRepository;
import com.example.DBS.repository.MotionRepository;
import com.example.DBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MotionFunctionService {
    private final MotionFunctionRepository motionFunctionRepository;
    private final UserRepository userRepository;
    private final MotionRepository motionRepository;
    private final FunctionRepository functionRepository;
    @Transactional
    public void saveMotionFunction(List<MotionFunctionDTO> motionFunctionList){
        Long userId = motionFunctionList.get(0).getUserId();
        User user = userRepository.findOne(userId);
        Motion motion = null;
        Function function = null;
        for(MotionFunctionDTO motionFunctionDTO: motionFunctionList){
            if(motionFunctionDTO.getUserId() != userId){
                throw new IllegalStateException("유저 ID가 잘못되었습니다.");
            }
            motion = motionRepository.findByName(motionFunctionDTO.getMotion());
            function = functionRepository.findByName(motionFunctionDTO.getFunction());
            if(motion == null || function == null){
                throw new IllegalStateException("모션, 기능 이름이 잘못되었습니다.");
            }
            MotionFunction motionFunction = new MotionFunction();
            motionFunction.setUser(user);
            motionFunction.setMotion(motion);
            motionFunction.setFunction(function);
            motionFunctionRepository.save(motionFunction);
        }
    }

    @Transactional
    public void setDefaultMotionSetting(Long userId){
        User user = userRepository.findOne(userId);
        List<Motion> motions = motionRepository.findAll();
        List<Function> functions = functionRepository.findAll();
        for(int i = 0; i<7; i++){
            MotionFunction motionFunction = new MotionFunction();
            motionFunction.setUser(user);
            motionFunction.setMotion(motions.get(i));
            motionFunction.setFunction(functions.get(i));
            motionFunctionRepository.save(motionFunction);
        }
    }

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
            result.add(new MotionFunctionDTO(mf.getUser().getId(),mf.getMotion().getName(),mf.getFunction().getName()));
        }
        return result;
    }
}
