package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Deploy;
import org.platform.quartz.deploy.replace.Order;

/**
 * @author twcao
 * @description 是否压缩打包后的文件
 * @project task-scheduler
 * @classname Compress
 * @date 2020/1/17 12:48
 */
public interface Compress extends Order, Deploy {

    @Override
    default int getOrder() {
        return 5;
    }

}
