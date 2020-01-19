package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.ssh.ganymed.GanymedSecureShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author twcao
 * @description linux文件上传
 * @project task-scheduler
 * @classname LinuxUpload
 * @date 2020/1/17 13:59
 */
public class LinuxUpload implements Upload {

    public static final Logger logger = LoggerFactory.getLogger(LinuxUpload.class);

    @Override
    public void execute(Context ctx) {
        GanymedSecureShell secureShell = new GanymedSecureShell(ctx.getString("deploy.upload.target.hostname"));
        //secureShell.setStopIfAbsent(false);
        secureShell.login(ctx.getString("deploy.upload.target.username"), ctx.getString("deploy.upload.target.password"));
        String compressFile = ctx.getString("tmp.compress.target.file");
        secureShell.put(Arrays.asList(compressFile), ctx.getString("deploy.upload.target.outcome.path"));
        secureShell.close();
        logger.info("部署文件上传完成, 远程主机路径 = {}", ctx.getString("deploy.upload.target.outcome.path"));
    }
}
