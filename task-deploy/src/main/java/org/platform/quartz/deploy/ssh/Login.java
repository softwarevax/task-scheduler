package org.platform.quartz.deploy.ssh;

/**
 * @author twcao
 * @description 登录
 * @project task-scheduler
 * @classname Login
 * @date 2020/1/10 16:54
 */
public interface Login {

    boolean login(String username, String password);
}
