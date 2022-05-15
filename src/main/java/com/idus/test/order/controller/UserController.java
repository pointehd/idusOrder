package com.idus.test.order.controller;

import com.idus.test.order.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    @GetMapping
    public int test(@RequestParam(required = false, defaultValue = "1") int n) {
        log.info("login info: {}", SecurityUtil.getCurrentMemberId());
        return n;
    }
}
