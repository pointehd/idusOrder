package com.idus.test.order.domain.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "User")
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
}
