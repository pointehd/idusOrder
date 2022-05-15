package com.idus.test.order.config.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties("application.data")
public class SecurityProperties {
    private String secret;
    private long expiration;
}
