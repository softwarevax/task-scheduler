package org.platform.quartz.deploy.deploy;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author twcao
 * @description 部署任务
 * @project task-scheduler
 * @classname DeployTask
 * @date 2020/1/16 10:37
 */
public class DeployJob implements Job {

    public static final Logger logger = LoggerFactory.getLogger(DeployJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap map = context.getMergedJobDataMap();
    }
}
