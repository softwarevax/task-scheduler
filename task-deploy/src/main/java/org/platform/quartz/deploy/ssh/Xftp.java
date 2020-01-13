package org.platform.quartz.deploy.ssh;

import java.util.List;

/**
 * @author twcao
 * @description 文件上传和下载
 * @project task-scheduler
 * @classname Xftp
 * @date 2020/1/13 9:57
 */
public interface Xftp {

    /**
     * 上传文件
     * @param localPaths 本地路径
     * @param remotePath 远程路径
     * @return 是否成功
     */
    boolean put(List<String> localPaths, String remotePath);

    /**
     * 下载文件
     * @param localPath 本地路径
     * @param remotePaths 远程路径
     * @return 是否成功
     */
    boolean get(String localPath, List<String> remotePaths);
}
