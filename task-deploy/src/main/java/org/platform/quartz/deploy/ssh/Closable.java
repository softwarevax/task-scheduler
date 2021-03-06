package org.platform.quartz.deploy.ssh;

/**
 * @author twcao
 * @description 关闭连接
 * @project task-scheduler
 * @classname Closable
 * @date 2020/1/10 16:56
 */
public interface Closable {

    /**
     * 关闭安全连接
     * @return 是否关闭成功
     */
    boolean close();
}
