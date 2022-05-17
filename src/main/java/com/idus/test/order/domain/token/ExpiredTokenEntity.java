package com.idus.test.order.domain.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "expired_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class ExpiredTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;
    String token;
    LocalDateTime createDatetime;

    public static ExpiredTokenEntity from(String token) {
        return ExpiredTokenEntity.builder()
                .token(token)
                .build();
    }
}
