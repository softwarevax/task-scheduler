package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.replace.DeployReplaceConfig;
import org.platform.quartz.deploy.replace.DeployReplaceHandler;
import org.platform.quartz.deploy.replace.ReplaceHandle;
import org.platform.quartz.deploy.replace.ReplaceStep;
import org.platform.quartz.deploy.utils.Constants;
import org.platform.quartz.deploy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author twcao
 * @description 文件替换
 * @project task-scheduler
 * @classname FileReplace
 * @date 2020/1/17 13:40
 */
public class FileReplace implements Replace {

    public static final Logger logger = LoggerFactory.getLogger(FileReplace.class);

    @Override
    public boolean execute(Context ctx) {
        String codePath = ctx.getString(Constants.TMP_PROJECT_SOURCE_CODE_PATH);
        if(StringUtils.isBlank(codePath)) {
            logger.error("The code path does not exist. Please confirm that the code has pulled successfully");
            return false;
        }
        // all properties of the current operation
        Map<String, String> properties = ctx.getPrefix(Constants.FileReplace.PROPERTY_PREFIX);
        Map<String, List<String>> groupBy = properties.keySet().stream().collect(Collectors.groupingBy(key -> key.substring(0, key.indexOf("]") + 1)));
        Iterator<Map.Entry<String, List<String>>> iterator = groupBy.entrySet().iterator();
        ReplaceHandle handle = new DeployReplaceHandler();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> entry = iterator.next();
            String key = entry.getKey();
            int idx = Integer.valueOf(entry.getKey().substring(key.indexOf("[") + 1, key.indexOf("]")));
            ReplaceStep step = new DeployReplaceConfig(
                codePath, ctx.getString(Constants.FileReplace.PROPERTY_PREFIX + "[" + idx  + "]." + Constants.FileReplace.SUFFIX_FILE_PATH),
                ctx.getString(Constants.FileReplace.PROPERTY_PREFIX + "[" + idx + "]." + Constants.FileReplace.SUFFIX_OLD),
                ctx.getString(Constants.FileReplace.PROPERTY_PREFIX + "[" + idx + "]." + Constants.FileReplace.SUFFIX_TARGET),
                ctx.getString(Constants.FileReplace.PROPERTY_PREFIX + "[" + idx + "]." + Constants.FileReplace.SUFFIX_FILTER_FILE_NAME),
                idx
            );
            if(step == null) {
                continue;
            }
            handle.addStep(step);
        }
        return handle.doReplace();
    }
}
