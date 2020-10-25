package org.springframework.util;

import org.springframework.lang.Nullable;

public class ObjectUtils {

    private static final String EMPTY_STRING = "";

    public static String identityToString(@Nullable Object obj) {
        if (obj == null) {
            return EMPTY_STRING;
        }
        String className = obj.getClass().getName();
        String identityHexString = getIdentityHexString(obj);
        return className + '@' + identityHexString;
    }

    public static String getIdentityHexString(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }
}
