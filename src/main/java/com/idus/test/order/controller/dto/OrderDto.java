package com.idus.test.order.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.idus.test.order.domain.order.OrderInfoEntity;
import lombok.*;
import org.hibernate.criterion.Order;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class OrderDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class OrderResponse {
        String orderId;
        String product;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createDatetime;

        public static OrderResponse from(OrderInfoEntity entity) {
            return OrderResponse.builder()
                    .orderId(entity.getOrderNum())
                    .product(entity.getProductName())
                    .createDatetime(entity.getCreateDatetime())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateOrder {
        @Size(min = 2, max = 100, message = "제품이름은 2~100글자 입니다.")
        String product;
    }
}
