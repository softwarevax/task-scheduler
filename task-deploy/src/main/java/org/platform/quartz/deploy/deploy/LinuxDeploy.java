package org.platform.quartz.deploy.deploy;

/**
 * @author twcao
 * @description linux系统部署
 * @project task-scheduler
 * @classname LinuxDeploy
 * @date 2020/1/16 11:08
 */
public class LinuxDeploy extends AbstractDeploy {

    @Override
    public boolean execute(Context ctx) {
        super.execute(ctx);
        return true;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
