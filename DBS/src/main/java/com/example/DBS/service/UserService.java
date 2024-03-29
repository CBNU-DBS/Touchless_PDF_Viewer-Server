package com.example.DBS.service;

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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * User 관련 서비스 로직
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final MotionFunctionRepository motionFunctionRepository;
    private final UserRepository userRepository;
    private final MotionRepository motionRepository;
    private final FuncRepository funcRepository;

    /**
     * 회원가입 관련 서비스 로직
     * @param user 유저 정보
     * @return 유저 아이디
     */
    @Transactional
    public Long signUp(User user){
        validateDuplicateUser(user);
        user.setPassword(getEncryptPassword(user.getPassword()));
        userRepository.save(user);

        List<Motion> motions = motionRepository.findAll();
        List<Func> funcs = funcRepository.findAll();
        for(int i = 0; i<7; i++){
            MotionFunction motionFunction = new MotionFunction();
            motionFunction.setUser(user);
            motionFunction.setMotion(motions.get(i));
            motionFunction.setFunc(funcs.get(i));
            motionFunctionRepository.save(motionFunction);
        }
        return user.getId();
    }

    /**
     * 비밀번호 암호화
     * @param password 비밀번호
     * @return  암호화된 비밀번호
     */
    private String getEncryptPassword(String password){
        MessageDigest md = null;
        String result = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] encryptedPassword = md.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b: encryptedPassword){
                sb.append(String.format("%02x", b));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(result == null){
            throw new IllegalStateException("비밀번호 암호화 오류입니다.");
        }
        return result;
    }
    private void validateDuplicateUser(User user){
        List<User> findUsers = userRepository.findByEmail(user.getEmail());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    /**
     * 로그인 관련 서비스 로직
     * @param user 유저 정보
     * @return 로그인한 유저 정보
     */
    @Transactional
    public User login(User user){
        List<User> findUsers = userRepository.findByEmail(user.getEmail());
        if(findUsers.size() != 1){
            throw new IllegalStateException("유효하지 않은 이메일입니다.");
        }
        String inputPassword = getEncryptPassword(user.getPassword());
        String findPassword = findUsers.get(0).getPassword();
        if(!inputPassword.equals(findPassword)){
            throw new IllegalStateException("비밀번호가 유효하지 않습니다.");
        }
        return findUsers.get(0);
    }

    /**
     * 비밀번호 변경 서비스 로직
     * @param user 유저 정보
     */
    @Transactional
    public void changepassword(User user){
        String newpw = getEncryptPassword(user.getPassword());
        user.setPassword(newpw);
        userRepository.changepasswordById(user.getId(), user.getPassword());
        System.out.println("입력된 후 패스워드값 확인 : " + user.getPassword());
        // 비밀번호가 정상적으로 변경되었는지 확인합니다.
        if(user.getPassword() != newpw){
            throw new IllegalStateException("비밀번호가 변경되지 않았습니다.");
        }
    }
}
