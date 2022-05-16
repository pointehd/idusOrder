package com.idus.test.order.controller;

import com.idus.test.order.config.security.JwtTokenProvider;
import com.idus.test.order.config.security.Token;
import com.idus.test.order.config.security.UserAuthentication;
import com.idus.test.order.controller.dto.UserDto;
import com.idus.test.order.domain.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController  {

    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "로그인", notes = "결과 값으로 나온 토큰을 상단에 붙혀넣기")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Token.Request request) throws Exception {

        UserDto.UserInfo user = userService.findByUserName(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("없는 사용자입니다."));
        if(!userService.matchPassword(request)){
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        Authentication authentication = new UserAuthentication(request.getId(), null, null);
        String token = jwtTokenProvider.generateToken(authentication);

        Token.Response response = Token.Response.builder().token(token).build();

        return ResponseEntity.ok(response);
    }

}
