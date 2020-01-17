package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.ssh.ganymed.GanymedSecureShell;

import java.util.Arrays;

/**
 * @author twcao
 * @description linux文件上传
 * @project task-scheduler
 * @classname LinuxUpload
 * @date 2020/1/17 13:59
 */
public class LinuxUpload implements Upload {

    @Override
    public void execute(Context ctx) {
        String codePath = ctx.getString("tmp.code.path");
        GanymedSecureShell secureShell = new GanymedSecureShell(ctx.getString("deploy.target.hostname"));
        secureShell.setStopIfAbsent(false);
        secureShell.login(ctx.getString("deploy.target.username"), ctx.getString("deploy.target.password"));
        secureShell.put(Arrays.asList(codePath + "." + ctx.getString("deploy.file.compress.suffix")), ctx.getString("deploy.target.outcome.path"));
        secureShell.close();
    }

    @Override
    public int getOrder() {
        return 6;
    }
}
