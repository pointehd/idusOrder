package com.idus.test.order.controller;

import com.idus.test.order.controller.dto.UserDto;
import com.idus.test.order.domain.user.UserService;
import com.idus.test.order.utils.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    UserService userService;

    @GetMapping
    public ResponseEntity<UserDto.UserInfo> userDetails() {
        log.debug("login info: {}", SecurityUtil.getCurrentMemberId());
        String userName = (String) SecurityUtil.getCurrentMemberId().getPrincipal();
        return ResponseEntity.ok(userService.findByUserName(userName).get());
    }
}
