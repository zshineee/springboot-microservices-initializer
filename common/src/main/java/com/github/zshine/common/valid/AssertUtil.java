package com.github.zshine.common.valid;

import com.github.zshine.common.exception.BusinessException;

public class AssertUtil {

    public static void notNull(Object obj, String errMsg) {
        if (obj == null) {
            throw new BusinessException(errMsg);
        }
    }
}
