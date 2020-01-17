package org.platform.quartz.deploy.utils;

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

}
