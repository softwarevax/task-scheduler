package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;

/**
 * @author twcao
 * @description 部署初始化
 * @project task-scheduler
 * @classname DeployInit
 * @date 2020/1/17 14:41
 */
public class DeployInit implements Init {

    @Override
    public void execute(Context ctx) {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
