package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Deploy;
import org.platform.quartz.deploy.replace.Order;

/**
 * @author twcao
 * @description 待上传的文件，筛选过滤
 * @project task-scheduler
 * @classname Filter
 * @date 2020/1/17 12:48
 */
public interface Filter extends Order, Deploy {

    @Override
    default int getOrder() {
        return 4;
    }

}
