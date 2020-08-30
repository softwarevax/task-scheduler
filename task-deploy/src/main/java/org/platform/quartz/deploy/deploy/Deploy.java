package org.platform.quartz.deploy.deploy;

import org.platform.quartz.deploy.deploy.listener.DeployListener;
import org.platform.quartz.deploy.replace.Order;

/**
 * @author twcao
 * @description 部署
 * @project task-scheduler
 * @classname Deploy
 * @date 2020/1/16 10:57
 */
public interface Deploy extends Order {

    default DeployListener getListener() {return null;}

    boolean execute(Context ctx);

    default void setListener(DeployListener listener) {}
}
