package org.platform.quartz.deploy.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.deploy.utils
 * @Description: 集合工具类
 * @date 2020/8/30 12:26
 */
public class CollectionUtils {

    public static boolean isBlank(Collection<?> coll) {
        if(coll == null || coll.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isBlank(Map coll) {
        if(coll == null || coll.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isAnyBlank(List<?> ... coll) {
        for(List<?> c : coll) {
            if(isBlank(c)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> endWith(final List<String> coll, String endWith) {
        List<String> list = new ArrayList<>();
        if(isBlank(coll) || StringUtils.isBlank(endWith)) return list;
        for(String str : coll) {
            if(str.endsWith(endWith)) {
                list.add(str);
            }
        }
        return list;
    }
}
