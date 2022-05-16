package com.idus.test.order.domain.user;

import com.idus.test.order.config.security.Token;
import com.idus.test.order.controller.dto.UserDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public Optional<UserEntity> getOptionalUserByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    public Optional<UserDto.UserInfo> getUserInfoByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .map(UserDto.UserInfo::from);
    }

    public boolean matchPassword(Token.Request request) {
        return userRepository.findByUserName(request.getId())
                .map(UserEntity::getPassword)
                .map(password -> passwordEncoder.matches(request.getSecret(), password))
                .orElse(false);
    }

    public boolean singUser(UserDto.SignupReqeust request) {
        userNameMatchThrowError(request.getUserName());
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(UserEntity.from(request));
        return true;
    }

    private void userNameMatchThrowError(String userName) {
        if (userRepository.findByUserName(userName).isPresent()) {
            throw new IllegalArgumentException("아이디 중복입니다.");
        }
    }

    // TODO
    public List<UserDto.UserInfo> getUserListBy(String search) {
        return userRepository.getUserList()
                .stream()
                .map(UserDto.UserInfo::from)
                .collect(Collectors.toList());
    }
}
