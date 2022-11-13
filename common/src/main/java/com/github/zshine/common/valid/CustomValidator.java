package com.github.zshine.common.valid;

import com.github.zshine.common.exception.EnumMsg;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CustomValidatorConstraint.class})
public @interface CustomValidator {

    String name();

    /**
     * 字段最小长度
     */
    int min() default 0;

    /**
     * 字段最大长度
     */
    int max() default Integer.MAX_VALUE;

    /**
     * 正则校验
     */
    PatternEnum pattern() default PatternEnum.NULL;

    /**
     * 枚举校验，枚举必须继承{@link EnumMsg}
     */
    Class<? extends Enum<?>>[] enumClass() default {};

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
