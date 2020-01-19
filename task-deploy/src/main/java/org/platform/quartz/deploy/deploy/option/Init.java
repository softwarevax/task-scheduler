package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Deploy;
import org.platform.quartz.deploy.replace.Order;

/**
 * @author twcao
 * @description 部署的初始化工作
 * @project task-scheduler
 * @classname Init
 * @date 2020/1/17 12:44
 */
public interface Init extends Order, Deploy {

    @Override
    default int getOrder() {
        return 0;
    }
}
