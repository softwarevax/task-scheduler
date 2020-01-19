package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.replace.DeployReplaceConfig;
import org.platform.quartz.deploy.replace.DeployReplaceHandler;
import org.platform.quartz.deploy.replace.ReplaceHandle;
import org.platform.quartz.deploy.replace.ReplaceStep;
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

    public static final String PROPERTY_PREFIX = "deploy.replace";

    @Override
    public void execute(Context ctx) {
        String codePath = ctx.getString("tmp.code.path");
        if(StringUtils.isBlank(codePath)) {
            logger.error("代码路径不存在, 请确认代码已拉取成功");
        }
        // 当前操作的所有属性
        Map<String, String> properties = ctx.getPrefix(PROPERTY_PREFIX);
        Map<String, List<String>> groupBy = properties.keySet().stream().collect(Collectors.groupingBy(key -> key.substring(0, key.indexOf('.', 10))));
        Iterator<Map.Entry<String, List<String>>> iterator = groupBy.entrySet().iterator();
        ReplaceHandle handle = new DeployReplaceHandler();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> entry = iterator.next();
            List<String> values = entry.getValue();
            String key = entry.getKey();
            int idx = Integer.valueOf(entry.getKey().substring(key.indexOf("[") + 1, key.indexOf("]")));
            ReplaceStep step = null;
            for(int i = 0, len = values.size(); i < len; i++) {
                step = new DeployReplaceConfig(
                        codePath, ctx.getString("deploy.replace[" + idx  + "].file.path"),
                        ctx.getString("deploy.replace[" + idx + "].old"),
                        ctx.getString("deploy.replace[" + idx + "].target"),
                        ctx.getString("deploy.replace[" + idx + "].filter.file.name"),
                        idx
                );
            }
            if(step == null) {
                continue;
            }
            handle.addStep(step);
        }
        boolean flag = handle.doReplace();
        logger.info("文件替换完成, 替换结果 = {}", flag);
    }
}
