package org.platform.quartz.deploy.ssh;

/**
 * @author twcao
 * @description 登录和退出
 * @project task-scheduler
 * @classname Connection
 * @date 2020/1/10 16:57
 */
public interface SecureShell extends Login, Execution, Closable {
}
