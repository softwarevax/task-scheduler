package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author twcao
 * @description 部署初始化
 * @project task-scheduler
 * @classname DeployInit
 * @date 2020/1/17 14:41
 */
public class DeployInit implements Init {

    public final Logger logger = LoggerFactory.getLogger(DeployInit.class);

    @Override
    public boolean execute(Context ctx) {
        logger.info("部署初始化。。。");
        return true;
    }
}
