package org.platform.quartz.deploy.deploy.option;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.maven.CompressHelper;
import org.platform.quartz.deploy.utils.Constants;
import org.platform.quartz.deploy.utils.Constants.ArchiveCompress;
import org.platform.quartz.deploy.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    public boolean execute(Context ctx) {
        if(!ctx.getBoolean(ArchiveCompress.COMPRESS_ENABLE, false)) {
            return false;
        }
        String targetPath = ctx.getString(ArchiveCompress.COMPRESS_TARGET_PATH);
        String compressFileName = FileUtils.getFileNameWithExt(ctx.getString(Constants.TMP_PROJECT_SOURCE_CODE_PATH));
        String tmpCompressFile = targetPath + File.separator + compressFileName + "." + ctx.getString(ArchiveCompress.COMPRESS_FILE_SUFFIX);
        List<String> toCompressFileList = JSONArray.parseArray(ctx.getString(Constants.FileFilter.TMP_FILTER_FILES), String.class);
        CompressHelper.compress(tmpCompressFile, toCompressFileList);
        List<String> filterFiles = new ArrayList<>();
        filterFiles.add(tmpCompressFile);
        ctx.put(Constants.FileFilter.TMP_FILTER_FILES, JSON.toJSONString(filterFiles));
        return true;
    }
}
