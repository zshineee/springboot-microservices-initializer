package com.github.zshine.common.valid;

import com.github.zshine.common.exception.EnumMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
class CustomValidatorConstraint implements ConstraintValidator<CustomValidator, Object> {

    Object name;

    int max;

    int min;

    Class<? extends Enum<?>>[] enumClass;

    PatternEnum patternEnum;

    @Override
    public void initialize(CustomValidator constraintAnnotation) {
        name = constraintAnnotation.name();
        enumClass = constraintAnnotation.enumClass();
        max = constraintAnnotation.max();
        min = constraintAnnotation.min();
        patternEnum = constraintAnnotation.pattern();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String arrayList = "java.util.ArrayList", hashSet = "java.util.HashSet";

        List<String> errMsg = new ArrayList<>();
        try {

            if (arrayList.equals(value.getClass().getName())) {
                List<Object> list = (List<Object>) value;
                for (Object obj : list) {
                    //长度是否正确
                    this.checkSize(obj, errMsg);
                    //格式校验
                    if (!this.isMatch(obj, context, errMsg)) {
                        break;
                    }
                }
            } else if (hashSet.equals(value.getClass().getName())) {
                Set<Object> set = (Set<Object>) value;
                for (Object obj : set) {
                    //长度是否正确
                    this.checkSize(obj, errMsg);
                    //格式校验
                    if (!this.isMatch(obj, context, errMsg)) {
                        break;
                    }
                }
            } else {
                //长度是否正确
                this.checkSize(value, errMsg);
                //格式校验
                this.isMatch(value, context, errMsg);
            }


            if (!errMsg.isEmpty()) {
                customMessageForValidation(context, String.join(",", errMsg));
                return false;
            }

            return true;
        } catch (ClassCastException e) {
            log.error("参数校验异常,可能是枚举未继承 EnumMsg 接口:" + e.getMessage());
            customMessageForValidation(context, "参数校验异常");
            return false;
        } catch (Exception e) {
            log.error("参数校验异常:" + e.getMessage());
            customMessageForValidation(context, "参数校验异常");
            return false;
        }
    }


    private void customMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
        constraintContext.disableDefaultConstraintViolation();
        constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

    /**
     * 校验格式
     */
    private boolean isMatch(Object value, ConstraintValidatorContext context, List<String> errMsg) {
        if (!patternEnum.pattern.equals(PatternEnum.NULL.pattern)) {
            Pattern compile = Pattern.compile(patternEnum.pattern);
            Matcher matcher = compile.matcher(value.toString());
            if (!matcher.matches()) {
                errMsg.add(name + "格式错误");
                return false;
            }
        }
        //校验枚举
        if (!ObjectUtils.isEmpty(enumClass)) {
            List<String> types = new ArrayList<>();
            try {
                Method method = enumClass[0].getMethod("values");
                EnumMsg[] inter = (EnumMsg[]) method.invoke(null, (Object[]) null);
                for (EnumMsg enumMessage : inter) {
                    types.add(enumMessage.getCode());
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                log.error("校验枚举异常:" + e.getMessage());
                customMessageForValidation(context, "校验枚举异常");
                return false;
            }
            if (!types.contains(value.toString())) {
                errMsg.add(name + "格式错误");
                return false;
            }
        }
        return true;
    }

    /**
     * 校验长度
     */
    private void checkSize(Object obj, List<String> errMsg) {
        if (obj.toString().length() > max) {
            errMsg.add(name + "应不超过最大长度[" + max + "]");
        }
        if (obj.toString().length() < min) {
            errMsg.add(name + "应不小于长度[" + min + "]");
        }
    }

}