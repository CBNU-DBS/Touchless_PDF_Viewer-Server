package com.example.DBS.service;

import com.example.DBS.domain.MotionFunction;
import com.example.DBS.domain.User;
import com.example.DBS.repository.MotionFunctionRepository;
import com.example.DBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MotionFunctionService {
    private final MotionFunctionRepository motionFunctionRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveMotionFunction(Long userId, MotionFunction motionFunction){
        User findUser = userRepository.findOne(userId);
        List<MotionFunction> motionFunctionList = motionFunctionRepository.findByUser(findUser);
        if(motionFunctionList.isEmpty()){
            motionFunctionRepository.save(motionFunction);
        }
    }
}
