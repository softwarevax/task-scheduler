package org.platform.quartz.deploy.deploy.option;

import com.alibaba.fastjson.JSON;
import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.utils.CollectionUtils;
import org.platform.quartz.deploy.utils.Constants;
import org.platform.quartz.deploy.utils.FileUtils;
import org.platform.quartz.deploy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author twcao
 * @description 文件过滤
 * @project task-scheduler
 * @classname FileFilter
 * @date 2020/1/17 15:24
 */
public class FileFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(FileFilter.class);

    @Override
    public boolean execute(Context ctx) {
        String codePath = ctx.getString(Constants.TMP_PROJECT_SOURCE_CODE_PATH);
        if(StringUtils.isBlank(codePath)) {
            logger.error("the code path does not exist. Please confirm that the code has pulled successfully");
            return false;
        }
        Map<String, String> properties = ctx.getPrefix(Constants.FileFilter.PROPERTY_PREFIX);
        if(CollectionUtils.isBlank(properties)) {
            return false;
        }
        String filterPath = properties.get(Constants.FileFilter.PATH);
        List<String> files = new ArrayList<>();
        List<String> filtered = new ArrayList<>();
        File filterRootPath = new File(FileUtils.merge(codePath, filterPath));
        if(filterRootPath.isFile()) {
            filtered.add(filterRootPath.getAbsolutePath());
        } else {
            FileUtils.files(filterRootPath.getAbsolutePath(), files);
            if(CollectionUtils.isBlank(files)) {
                return false;
            }
            String exts = properties.get(Constants.FileFilter.FILE_EXT);
            List<String> extList = StringUtils.split(exts, ",");
            if(CollectionUtils.isBlank(extList)) {
                return false;
            }
            for(int i = 0, len = files.size(); i < len; i++) {
                String fileName = files.get(i);
                for(String ext : extList) {
                    if(fileName.endsWith(ext)) {
                        filtered.add(files.get(i));
                    }
                }
            }
        }
        if(CollectionUtils.isBlank(filtered)) {
            return false;
        }
        ctx.put(Constants.FileFilter.TMP_FILTER_FILES, JSON.toJSONString(filtered));
        return true;
    }
}
