package com.idus.test.order.controller.dto;

import lombok.*;

public class UserDto {

    @NoArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class UserInfo {
        String userName;
        String nickname;
        String phone;
        String email;
        String gender;
    }
}
