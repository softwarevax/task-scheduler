package org.platform.quartz.deploy.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author twcao
 * @description string工具类
 * @project task-scheduler
 * @classname StringUtils
 * @date 2020/1/13 9:13
 */
public class StringUtils {

    public static final boolean isBlank(CharSequence cs) {
        if(cs == null || cs.length() == 0) {
            return true;
        }
        return false;
    }

    public static final boolean isAnyBlank(CharSequence ... css) {
        if(css.length == 0) {
            return true;
        }
        for(CharSequence cs : css) {
            if(isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    public static final String substr(String str, int spos, int epos) {
        if(StringUtils.isBlank(str)) {
            return str;
        }
        return str.substring(spos, epos);
    }

    public static final boolean equals(CharSequence str1, CharSequence str2) {
        if(str1 == str2) {
            return true;
        }
        if(str1 == null || str2 == null) {
            return false;
        }
        if(str1.length() != str2.length()) {
            return false;
        }
        for(int length = str1.length(), i = 0; i < length; i++) {
            if(str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static final List<String> split(String str, String split) {
        List<String> result = new ArrayList<>();
        if(StringUtils.isBlank(str)) {
            return result;
        }
        if(StringUtils.isBlank(split)) {
            result.add(str);
            return result;
        }
        String[] splitArr = str.split(split);
        return Arrays.asList(splitArr);
    }
}
