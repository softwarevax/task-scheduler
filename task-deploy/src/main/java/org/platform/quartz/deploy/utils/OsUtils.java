package org.platform.quartz.deploy.utils;

/**
 * @author twcao
 * @description 系统工具类
 * @project task-scheduler
 * @classname OsUtils
 * @date 2020/1/15 18:57
 */
public class OsUtils {

    public static String os() {
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("windows")) {
            return "windows";
        } else if(os.toLowerCase().startsWith("linux")) {
            return "linux";
        }
        return "";
    }
}
