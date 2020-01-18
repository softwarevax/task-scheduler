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
        GanymedSecureShell secureShell = new GanymedSecureShell(ctx.getString("deploy.upload.target.hostname"));
        secureShell.setStopIfAbsent(false);
        secureShell.login(ctx.getString("deploy.upload.target.username"), ctx.getString("deploy.upload.target.password"));
        String compressFile = ctx.getString("tmp.compress.target.file");
        secureShell.put(Arrays.asList(compressFile), ctx.getString("deploy.upload.target.outcome.path"));
        secureShell.close();
    }

    @Override
    public int getOrder() {
        return 6;
    }
}
