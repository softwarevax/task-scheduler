package org.platform.quartz.deploy.ssh;

/**
 * @author twcao
 * @description 命令执行
 * @project task-scheduler
 * @classname Execution
 * @date 2020/1/10 16:51
 */
public interface Execution {

    String execute(String cmd);

}
