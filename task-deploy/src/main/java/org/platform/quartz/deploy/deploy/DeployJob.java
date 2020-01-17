package org.platform.quartz.deploy.deploy;

import org.platform.quartz.deploy.deploy.option.*;
import org.platform.quartz.deploy.utils.PropertyUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
        Properties props = PropertyUtils.getProperty();//PropertyUtils.toProp(map);
        List<Class<? extends Deploy>> deploys = new ArrayList<>();
        deploys.add(DeployInit.class);
        deploys.add(GitFetch.class);
        deploys.add(FileReplace.class);
        deploys.add(MavenPack.class);
        deploys.add(FileFilter.class);
        deploys.add(TarArchiveCompress.class);
        deploys.add(LinuxUpload.class);
        deploys.add(LinuxRun.class);
        AbstractDeploy deploy = DeployFactory.getInstance(deploys);
        Context ctx = new Context();
        ctx.putAll(PropertyUtils.toMap(props));
        deploy.execute(ctx);
    }
}
