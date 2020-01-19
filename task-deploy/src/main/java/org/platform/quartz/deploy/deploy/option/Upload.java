package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Deploy;
import org.platform.quartz.deploy.replace.Order;

/**
 * @author twcao
 * @description 文件上传至主机
 * @project task-scheduler
 * @classname Upload
 * @date 2020/1/17 12:49
 */
public interface Upload extends Order, Deploy {

    @Override
    default int getOrder() {
        return 6;
    }
}
