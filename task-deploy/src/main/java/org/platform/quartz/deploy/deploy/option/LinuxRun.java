package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.ssh.ganymed.GanymedSecureShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author twcao
 * @description linux部署
 * @project task-scheduler
 * @classname LinuxRun
 * @date 2020/1/17 14:27
 */
public class LinuxRun implements Run {

    public static final Logger logger = LoggerFactory.getLogger(LinuxRun.class);

    public static final String PROPERTY_PREFIX = "deploy.run";

    @Override
    public void execute(Context ctx) {
        Map<String, String> properties = ctx.getPrefix(PROPERTY_PREFIX);
        if(CollectionUtils.isEmpty(properties)) {
            return;
        }
        Collection<String> keys = properties.keySet();
        List<String> steps = new ArrayList<>(keys);
        steps.stream().sorted(Comparator.comparing(key -> Integer.parseInt(key.substring(key.indexOf("[") + 1), key.indexOf("]"))));
        GanymedSecureShell secureShell = new GanymedSecureShell(ctx.getString("deploy.upload.target.hostname"));
        secureShell.login(ctx.getString("deploy.upload.target.username"), ctx.getString("deploy.upload.target.password"));
        for(int i = 0, len = steps.size(); i < len; i++) {
            String key = steps.get(i);
            String idx = key.substring(key.indexOf("[") + 1, key.indexOf("]"));
            secureShell.execute(ctx.getString("deploy.run.step[" + idx + "]"));
            logger.info("远程命令 {} 执行完成", ctx.getString("deploy.run.step[" + idx + "]"));
        }
        secureShell.close();
    }
}
