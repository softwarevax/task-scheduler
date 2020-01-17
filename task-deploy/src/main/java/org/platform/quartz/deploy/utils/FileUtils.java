package org.platform.quartz.deploy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author twcao
 * @description 文件工具类
 * @project task-scheduler
 * @classname FileUtils
 * @date 2020/1/15 18:43
 */
public class FileUtils {

    public static String merge(String rootPath, String relativePath) {
        if(StringUtils.isAnyBlank(rootPath, relativePath)) {
            return rootPath;
        }
        String path = rootPath + File.separator + relativePath;
        // 防止多余的文件分隔符
        if("windows".equals(OsUtils.os())) {
            path = path.replace("\\\\", "\\");
        }
        return path;
    }

    /**
     *替换file目录下，所有文件名是fileName的文件，将old换成replace
     * @param file (绝对路径) 具体的文件或文件路径
     * @param old 原本的字符串
     * @param fileName 文件名
     * @param replace 待替换的字符串
     * @param charset 字符集
     * @return 是否替换成功
     */
    public static boolean replace(String file, String fileName, String old, String replace, String charset) {
        File f = new File(file);
        if(!f.exists()) {
            return false;
        }
        List<String> files = new ArrayList<>();
        files(file, files);
        try {
            for (int i = 0, len = files.size(); i < len; i++) {
                String currentFile = files.get(i);
                if(StringUtils.isBlank(fileName)) {
                    replaceFile(currentFile, old, replace);
                    continue;
                }
                if(currentFile.endsWith(fileName)) {
                    replaceFile(currentFile, old, replace);
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean replace(String file, String fileName, String old, String replace) {
        return replace(file, fileName, old, replace, "utf-8");
    }

    public static boolean replace(String file, String old, String replace) {
        return replace(file, null, old, replace, "utf-8");
    }

    public static boolean replaceFile(String fileName, String old, String replace, String charset) throws FileNotFoundException {
        File file = new File(fileName);
        if(!file.exists()) {
            return false;
        }
        String content = IOUtils.toString(new FileInputStream(file), charset);
        content = content.replace(old, replace);
        IOUtils.write(content, file);
        return true;
    }

    public static boolean replaceFile(String fileName, String old, String replace) throws FileNotFoundException {
        return replaceFile(fileName, old, replace, "utf-8");
    }

    public static void files(String filePath, List<String> fileList) {
        File file = new File(filePath);
        if(file.isFile()) {
            fileList.add(file.getAbsolutePath());
            return;
        }
        File[] files = file.listFiles();
        for(File f : files) {
            files(f.getAbsolutePath(), fileList);
        }
    }
}
