package com.anibaba.core.util;

import java.util.List;

public class BaseUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }
}
