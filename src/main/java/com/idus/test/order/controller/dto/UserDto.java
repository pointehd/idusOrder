package com.idus.test.order.controller.dto;

import com.idus.test.order.domain.user.Gender;
import com.idus.test.order.domain.user.UserEntity;
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
        Gender gender;

        public static UserInfo from(UserEntity userEntity) {
            return UserInfo.builder()
                    .userName(userEntity.getUserName())
                    .nickname(userEntity.getNickname())
                    .phone(userEntity.getPhone())
                    .email(userEntity.getPhone())
                    .gender(userEntity.getGender())
                    .build();
        }
    }
}
