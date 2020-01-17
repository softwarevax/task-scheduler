package org.platform.quartz.deploy.deploy.listener;

import org.platform.quartz.deploy.deploy.Context;

import java.util.EventListener;

/**
 * @author twcao
 * @description 监听器
 * @project task-scheduler
 * @classname Listener
 * @date 2020/1/17 11:41
 */
public interface DeployListener extends EventListener {

    void before(Context ctx);

    void after(Context ctx);
}
