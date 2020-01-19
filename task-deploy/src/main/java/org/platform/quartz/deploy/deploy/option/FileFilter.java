package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.utils.FileUtils;
import org.platform.quartz.deploy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.*;

/**
 * @author twcao
 * @description 文件过滤
 * @project task-scheduler
 * @classname FileFilter
 * @date 2020/1/17 15:24
 */
public class FileFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(FileFilter.class);

    public static final String PROPERTY_PREFIX = "deploy.filter";

    @Override
    public void execute(Context ctx) {
        String codePath = ctx.getString("tmp.code.path");
        if(StringUtils.isBlank(codePath)) {
            logger.error("代码路径不存在, 请确认代码已拉取成功");
        }
        Map<String, String> properties = ctx.getPrefix(PROPERTY_PREFIX);
        if(CollectionUtils.isEmpty(properties)) {
            return;
        }
        Collection<String> fileExt = properties.values();
        String sourcePath = ctx.getString("deploy.compress.source.path");
        File tmpSourcePath = new File(sourcePath + ".tmp");
        if(!tmpSourcePath.exists()) {
            tmpSourcePath.mkdir();
        }
        FileUtils.copyFolder(sourcePath, tmpSourcePath.getAbsolutePath());
        List<String> files = new ArrayList<>();
        FileUtils.files(tmpSourcePath.getAbsolutePath(), files);
        for(int i = 0, len = files.size(); i < len; i++) {
            String fileName = files.get(i);
            boolean fit = false;
            Iterator<String> exts = fileExt.iterator();
            while (exts.hasNext()) {
                String ext = exts.next();
                if(fileName.endsWith(ext)) {
                    fit = true;
                    break;
                }
            }
            if(!fit) {
                // 删除不符合条件的文件
                FileUtils.forceDelete(fileName);
            }
        }
        ctx.put("tmp.compress.source.path", tmpSourcePath.getAbsolutePath());
    }
}
