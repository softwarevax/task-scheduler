package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.maven.CompressHelper;
import org.platform.quartz.deploy.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author twcao
 * @description TarArchive压缩
 * @project task-scheduler
 * @classname TarArchiveCompress
 * @date 2020/1/17 13:52
 */
public class TarArchiveCompress implements Compress {

    public static final Logger logger = LoggerFactory.getLogger(TarArchiveCompress.class);

    @Override
    public void execute(Context ctx) {
        if(!Boolean.parseBoolean(ctx.getString("deploy.compress.enable"))) {
            return;
        }
        String targetPath = ctx.getString("deploy.compress.target.path");
        String sourcePath = ctx.getString("deploy.compress.source.path");
        String tmpSourcePath = ctx.getString("tmp.compress.source.path");
        String compressFileName = sourcePath.substring(sourcePath.lastIndexOf(File.separator));
        String tmpCompressFile = targetPath + File.separator + compressFileName + "." + ctx.getString("deploy.compress.suffix");
        ctx.put("tmp.compress.target.file", tmpCompressFile);
        CompressHelper.compress(tmpCompressFile, new File(tmpSourcePath).listFiles());
        logger.info("文件压缩完成, 路径 = {}", tmpCompressFile);
        FileUtils.forceDelete(tmpSourcePath);
        logger.info("临时文件{}已删除", tmpSourcePath);
    }
}
