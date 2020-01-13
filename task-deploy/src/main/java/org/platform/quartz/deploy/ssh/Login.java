package org.platform.quartz.deploy.ssh;

/**
 * @author twcao
 * @description 登录
 * @project task-scheduler
 * @classname Login
 * @date 2020/1/10 16:54
 */
public interface Login {

    /**
     * 用户名密码登录
     * @param username 用户名
     * @param password 用户密码
     * @return 是否登录成功
     */
    boolean login(String username, String password);
}
