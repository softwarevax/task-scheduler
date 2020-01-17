package org.platform.quartz.deploy.git;

/**
 * @author twcao
 * @description 仓库
 * @project task-scheduler
 * @classname Repository
 * @date 2020/1/15 17:23
 */
public interface Repository {

    /**
     * 用户名
     * @return
     */
    String getUserName();

    /**
     * 密码
     * @return
     */
    String getPassword();

    /**
     * 仓库地址
     * @return
     */
    String getUri();

    /**
     * 拉取代码
     * @return
     */
    boolean fetch();

    /**
     * 获取源码的地址
     * @return
     */
    String getSourcePath();
}
