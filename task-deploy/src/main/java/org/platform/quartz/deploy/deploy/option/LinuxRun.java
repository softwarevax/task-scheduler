package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.ssh.ganymed.GanymedSecureShell;

/**
 * @author twcao
 * @description linux部署
 * @project task-scheduler
 * @classname LinuxRun
 * @date 2020/1/17 14:27
 */
public class LinuxRun implements Run {

    @Override
    public void execute(Context ctx) {
        GanymedSecureShell secureShell = new GanymedSecureShell(ctx.getString("deploy.target.hostname"));
        secureShell.login(ctx.getString("deploy.target.username"), ctx.getString("deploy.target.password"));
        secureShell.execute(ctx.getString("deploy.subsequent.step[0]"));
        secureShell.execute(ctx.getString("deploy.subsequent.step[1]"));
        secureShell.close();
    }

    @Override
    public int getOrder() {
        return 7;
    }
}
