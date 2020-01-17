package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;

/**
 * @author twcao
 * @description 文件过滤
 * @project task-scheduler
 * @classname FileFilter
 * @date 2020/1/17 15:24
 */
public class FileFilter implements Filter {

    @Override
    public void execute(Context ctx) {

    }

    @Override
    public int getOrder() {
        return 4;
    }
}
