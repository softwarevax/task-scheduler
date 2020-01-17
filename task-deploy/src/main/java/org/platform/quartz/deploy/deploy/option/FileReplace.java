package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.replace.DeployReplaceConfig;
import org.platform.quartz.deploy.replace.DeployReplaceHandler;
import org.platform.quartz.deploy.replace.ReplaceHandle;
import org.platform.quartz.deploy.replace.ReplaceStep;

/**
 * @author twcao
 * @description 文件替换
 * @project task-scheduler
 * @classname FileReplace
 * @date 2020/1/17 13:40
 */
public class FileReplace implements Replace {

    @Override
    public void execute(Context ctx) {
        String codePath = ctx.getString("tmp.code.path");
        ReplaceStep step = new DeployReplaceConfig(
                codePath, ctx.getString("git.deploy.replace.file.path"),
                ctx.getString("git.deploy.replace.old"),
                ctx.getString("git.deploy.replace.target"),
                ctx.getString("git.deploy.replace.filter.file.name"),
                1
        );

        ReplaceHandle handle = new DeployReplaceHandler();
        handle.addStep(step);
        handle.doReplace();
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
