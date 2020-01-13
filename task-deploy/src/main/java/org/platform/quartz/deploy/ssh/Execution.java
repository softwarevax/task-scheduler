package org.platform.quartz.deploy.ssh;

/**
 * @author twcao
 * @description 命令执行
 * @project task-scheduler
 * @classname Execution
 * @date 2020/1/10 16:51
 */
public interface Execution {

    /**
     * 执行shell命令
     * @param cmd shell/cmd命令
     * @return 控制台返回结果
     */
    String execute(String cmd);

}
