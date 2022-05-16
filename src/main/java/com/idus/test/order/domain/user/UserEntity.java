package com.idus.test.order.domain.user;

import com.idus.test.order.controller.dto.UserDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;
    String userName; // FIXME username & name 구분 필요?
    String nickname;
    String password;
    String phone;
    String email;
    @Enumerated(EnumType.STRING)
    Gender gender;

    @CreationTimestamp
    LocalDateTime createDatetime;
    @UpdateTimestamp
    LocalDateTime updateDatetime;

    @Builder
    public UserEntity(String userName, String nickname, String password, String phone, String email, Gender gender) {
        this.userName = userName;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

    public static UserEntity from(UserDto.SignupReqeust request) {
        return UserEntity.builder()
                .userName(request.getUserName())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .phone(request.getPhone())
                .email(request.getEmail())
                .gender(request.getGender())
                .build();
    }
}
