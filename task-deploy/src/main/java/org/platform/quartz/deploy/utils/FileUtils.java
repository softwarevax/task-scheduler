package org.platform.quartz.deploy.utils;

import java.io.*;
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

    /**
     * 获取目录下的所有文件名称
     * @param filePath
     * @param fileList
     */
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

    /**
     * 复制文件夹
     * @param resource
     * @param target
     */
    public static void copyFolder(String resource, String target) {
        File resourceFile = new File(resource);
        if (!resourceFile.exists()) {
            return;
        }
        File targetFile = new File(target);
        if (!targetFile.exists()) {
           return;
        }
        // 获取源文件夹下的文件夹或文件
        File[] resourceFiles = resourceFile.listFiles();
        for (File file : resourceFiles) {
            File file1 = new File(targetFile.getAbsolutePath() + File.separator + resourceFile.getName());
            // 复制文件
            if (file.isFile()) {
                // 在 目标文件夹（B） 中 新建 源文件夹（A），然后将文件复制到 A 中
                // 这样 在 B 中 就存在 A
                if (!file1.exists()) {
                    file1.mkdirs();
                }
                File targetFile1 = new File(file1.getAbsolutePath() + File.separator + file.getName());
                copyFile(file, targetFile1);
            }
            // 复制文件夹
            if (file.isDirectory()) {// 复制源文件夹
                String dir1 = file.getAbsolutePath();
                // 目的文件夹
                String dir2 = file1.getAbsolutePath();
                copyFolder(dir1, dir2);
            }
        }
    }

    /**
     * 复制文件
     *
     * @param resource
     * @param target
     */
    public static void copyFile(File resource, File target) {
        try {
            // 输入流 --> 从一个目标读取数据
            // 输出流 --> 向一个目标写入数据
            // 文件输入流并进行缓冲
            FileInputStream inputStream = new FileInputStream(resource);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            // 文件输出流并进行缓冲
            FileOutputStream outputStream = new FileOutputStream(target);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            // 缓冲数组
            // 大文件 可将 1024 * 2 改大一些，但是 并不是越大就越快
            byte[] bytes = new byte[1024 * 2];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }
            // 刷新输出缓冲流
            bufferedOutputStream.flush();
            //关闭流
            bufferedInputStream.close();
            bufferedOutputStream.close();
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 强制删除文件
     * @param path
     */
    public static void forceDelete(String path) {
        try {
            org.apache.commons.io.FileUtils.forceDelete(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
