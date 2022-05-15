package com.idus.test.order.domain.user;

import com.idus.test.order.config.security.Token;
import com.idus.test.order.controller.dto.UserDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;

    public Optional<UserDto.UserInfo> findByIdPw(String id) {
        return userRepository.findByUserName(id);
    }

    // TODO 미구현 상태  구현 필요
    public boolean matchPassword(Token.Request request) {
        return true;
    }
}
