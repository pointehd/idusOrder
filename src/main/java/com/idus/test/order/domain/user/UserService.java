package com.idus.test.order.domain.user;

import com.idus.test.order.config.security.Token;
import com.idus.test.order.controller.dto.UserDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public Optional<UserDto.UserInfo> findById(String id) {
        return userRepository.findByUserName(id)
                .map(UserDto.UserInfo::from);
    }

    public boolean matchPassword(Token.Request request) {
        return userRepository.findByUserName(request.getId())
                .map(UserEntity::getPassword)
                .map(password-> passwordEncoder.matches(request.getSecret(), password))
                .orElse(false);
    }

    public boolean singUser(UserDto.SignupReqeust request) {
        userNameMatchThrowError(request.getUserName());
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(UserEntity.from(request));
        return true;
    }

    public void userNameMatchThrowError(String userName) {
        if(userRepository.findByUserName(userName).isPresent()){
            throw new IllegalArgumentException("아이디 중복입니다.");
        }



    }
}
