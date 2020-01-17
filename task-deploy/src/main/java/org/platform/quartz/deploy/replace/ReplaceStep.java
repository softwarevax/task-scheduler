package org.platform.quartz.deploy.replace;

/**
 * @author twcao
 * @description 替换接口
 * @project task-scheduler
 * @classname ReplaceConfig
 * @date 2020/1/16 9:14
 */
public interface ReplaceStep extends Order {

    String getFilePath();

    String getOld();

    String getTarget();

    String getFilterFileName();

    String getCodePath();
}
