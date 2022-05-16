package com.idus.test.order.domain.order;

import com.idus.test.order.domain.user.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "order_info")
public class OrderInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;

    @JoinColumn(name = "user_seq", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    UserEntity user;

    String orderNum;
    String productName;

    @CreationTimestamp
    LocalDateTime createDatetime;

    @Builder
    public OrderInfoEntity(UserEntity user, String orderNum, String productName) {
        this.user = user;
        this.orderNum = orderNum;
        this.productName = productName;
    }

    // FIXME dto 만들어서 넘길지 고민
    public static OrderInfoEntity of(UserEntity user, String orderNum, String productName) {
        return OrderInfoEntity.builder()
                .user(user)
                .orderNum(orderNum)
                .productName(productName)
                .build();
    }
}
