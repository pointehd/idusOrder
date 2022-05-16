package com.idus.test.order.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumPatternValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumPattern {
    String regexp();

    String message() default "'{regexp}' 다음 값중하나 여야합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
