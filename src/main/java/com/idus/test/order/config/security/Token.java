package com.idus.test.order.config.security;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;
import lombok.experimental.FieldDefaults;

public class Token {

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Request {
        @ApiModelProperty(example = "test가나", notes = "가입시 username에 적은값")
        String id;
        @ApiModelProperty(example = "@aA12341234", notes = "가입시 password에 적은 값")
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
