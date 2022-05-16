package com.idus.test.order.controller;

import com.idus.test.order.controller.dto.UserDto;
import com.idus.test.order.domain.user.UserService;
import com.idus.test.order.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    UserService userService;

    @ApiOperation(value = "회원 상세 정보 조회")
    @GetMapping
    public ResponseEntity<UserDto.UserInfo> userDetails() {
        log.debug("login info: {}", SecurityUtil.getCurrentMemberId());
        String userName = (String) SecurityUtil.getCurrentMemberId().getPrincipal();
        return ResponseEntity.ok(userService.getUserInfoByUserName(userName).get());
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<List<UserDto.UserInfo>> pageUserBySearch(
            @RequestParam String name, @PathVariable int page) {
        return ResponseEntity.ok(userService.getUserListBy(name));
    }
}
