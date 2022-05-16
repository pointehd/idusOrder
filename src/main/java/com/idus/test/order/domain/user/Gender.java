package com.idus.test.order.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

@Slf4j
public enum Gender {
    MALE,
    FEMALE,
    NONE;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Gender findByCode(String code) {
        log.debug("Gender log", code);
        return Stream.of(Gender.values())
                .filter(c -> c.name().equals(code))
                .findFirst()
                .orElseGet(()-> StringUtils.hasText(code) ? NONE : null);
    }
}
