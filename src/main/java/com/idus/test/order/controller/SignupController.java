package com.idus.test.order.controller;

import com.idus.test.order.controller.dto.UserDto;
import com.idus.test.order.domain.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/signup")
public class SignupController {

    UserService userService;

    @PostMapping
    public ResponseEntity<Boolean> signup(
            @Valid @RequestBody
            UserDto.SignupReqeust request) {
        return ResponseEntity.ok(userService.singUser(request));
    }
}
