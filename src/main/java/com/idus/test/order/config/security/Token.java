package com.idus.test.order.config.security;

import lombok.*;
import lombok.experimental.FieldDefaults;

public class Token {

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Request {
        String id;
        String secret;
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static final class Response {
        String token;
    }
}
