package org.platform.quartz.deploy.replace;

/**
 * @author twcao
 * @description 配置文件替换操作
 * @project task-scheduler
 * @classname ReplaceStep
 * @date 2020/1/15 20:15
 */
public interface ReplaceHandle {

    void addStep(ReplaceStep step);

    boolean doReplace();
}
