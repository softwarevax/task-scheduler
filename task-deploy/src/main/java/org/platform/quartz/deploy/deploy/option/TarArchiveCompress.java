package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.maven.CompressHelper;

import java.io.File;

/**
 * @author twcao
 * @description TarArchive压缩
 * @project task-scheduler
 * @classname TarArchiveCompress
 * @date 2020/1/17 13:52
 */
public class TarArchiveCompress implements Compress {

    @Override
    public void execute(Context ctx) {
        if(!Boolean.parseBoolean(ctx.getString("deploy.file.compress"))) {
            return;
        }
        String targetPath = ctx.getString("deploy.file.compress.target.path");
        String sourcePath = ctx.getString("deploy.file.compress.source.path");
        CompressHelper.compress(targetPath + "." + ctx.getString("deploy.file.compress.suffix"), new File(sourcePath).listFiles());
    }

    @Override
    public int getOrder() {
        return 5;
    }
}
