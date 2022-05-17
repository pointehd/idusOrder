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

    @GetMapping("/search")
    public ResponseEntity<UserDto.UsersOrderPager> pageUserBySearch(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(name = "view_count", required = false, defaultValue = "10") int viewCount,
            @RequestParam(required = false) String search) {
        UserDto.UserSearchRequest request = UserDto.UserSearchRequest.builder()
                .page(page)
                .search(search)
                .viewCount(viewCount)
                .build();
        return ResponseEntity.ok(userService.getUserPagerBy(request));
    }
}
