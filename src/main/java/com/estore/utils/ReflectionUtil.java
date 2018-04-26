package com.estore.utils;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;

public class ReflectionUtil {

    /**
     * 对Spring的ReflectionUtils中getValue方法做简单封装
     *
     * @param object
     * @param key
     * @param defaultVal
     * @return
     */
    public static Object getValue(Object object, String key, Object defaultVal) {
        Field field = ReflectionUtils.findField(object.getClass(), key);
        if (Objects.isNull(field)) {
            return defaultVal;
        }
        field.setAccessible(true);
        return ReflectionUtils.getField(field, object);
    }
}
