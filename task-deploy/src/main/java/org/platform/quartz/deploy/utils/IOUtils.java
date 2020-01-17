package org.platform.quartz.deploy.utils;

import org.platform.quartz.deploy.utils.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author twcao
 * @description 流工具
 * @project task-scheduler
 * @classname IOUtils
 * @date 2020/1/13 9:29
 */
public class IOUtils {

    public static final String DEFAULT_CHARSET = "utf-8";

    /**
     * 换行
     * @param is
     * @param charset
     * @return
     */
    public static List<String> toStrings(InputStream is, String charset) {
        try(InputStreamReader isr = new InputStreamReader(is, StringUtils.isBlank(charset) ? DEFAULT_CHARSET : charset);
            BufferedReader br = new BufferedReader(isr)) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 不换行
     * @param is
     * @param charset
     * @return
     */
    public static String toString(InputStream is, String charset) {
        StringBuffer sb = new StringBuffer();
        try(InputStreamReader isr = new InputStreamReader(is, StringUtils.isBlank(charset) ? DEFAULT_CHARSET : charset);
            BufferedReader br = new BufferedReader(isr)) {
            int len = 0;
            char[] buff = new char[1024];
            while ((len = br.read(buff)) > 0) {
                sb.append(new String(buff, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void write(String content, OutputStream os) {
        try {
            os.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String content, File file) {
        try(FileOutputStream os = new FileOutputStream(file)) {
            os.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
