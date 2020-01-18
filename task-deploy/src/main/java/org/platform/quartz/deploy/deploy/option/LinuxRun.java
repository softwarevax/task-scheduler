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
        GanymedSecureShell secureShell = new GanymedSecureShell(ctx.getString("deploy.upload.target.hostname"));
        secureShell.login(ctx.getString("deploy.upload.target.username"), ctx.getString("deploy.upload.target.password"));
        secureShell.execute(ctx.getString("deploy.run.step[0]"));
        secureShell.execute(ctx.getString("deploy.run.step[1]"));
        secureShell.close();
    }

    @Override
    public int getOrder() {
        return 7;
    }
}
