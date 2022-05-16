package com.idus.test.order.controller.dto;

import com.idus.test.order.annotation.EnumPattern;
import com.idus.test.order.domain.user.Gender;
import com.idus.test.order.domain.user.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

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

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public static class SignupReqeust {
        @NotNull(message = "이름은 영문자 또는 한글이어야합니다.")
        @Pattern(regexp = "^[a-zA-z가-힣]{2,20}$", message = "이름은 영문자 또는 한글이어야합니다.")
        String userName;
        @NotNull(message = "닉네임은 소문자로 구성되어야합니다.")
        @Pattern(regexp = "[a-z]{2,30}", message = "닉네임은 소문자로 구성되어야합니다.")
        String nickname;
        @NotNull(message = "영문소문자, 영문대문자, 특수문자, 숫자가 포함된 10자 이상이야 합니다.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{10,}$",
                message = "영문소문자, 영문대문자, 특수문자, 숫자가 포함된 10자 이상이야 합니다.")
        String password;
        @NotNull(message = "010-0000-0000와 같은 형식으로 입력해야합니다.")
        @Pattern(regexp = "^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$", message = "010-0000-0000와 같은 형식으로 입력해야합니다.")
        String phone;
        @NotNull(message = "이메일은 필수사항 입니다.")
        @Email(message = "이메일 양식을 확인해주세요")
        String email;
        @EnumPattern(regexp = "MALE|FEMALE")
        Gender gender;
    }

}
