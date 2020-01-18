package org.platform.quartz.deploy.web.service.impl;

import org.platform.quartz.deploy.deploy.AbstractDeploy;
import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.deploy.Deploy;
import org.platform.quartz.deploy.deploy.DeployFactory;
import org.platform.quartz.deploy.deploy.option.*;
import org.platform.quartz.deploy.utils.PropertyUtils;
import org.platform.quartz.deploy.utils.StringUtils;
import org.platform.quartz.deploy.web.dao.DeployConfigureDao;
import org.platform.quartz.deploy.web.dao.TaskDeployDao;
import org.platform.quartz.deploy.web.entity.DeployConfigure;
import org.platform.quartz.deploy.web.entity.TaskDeployEntity;
import org.platform.quartz.deploy.web.service.TaskDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author twcao
 * @description 任务部署service实现
 * @project task-scheduler
 * @classname TaskDeployServiceImpl
 * @date 2020/1/18 16:06
 */
@Service
public class TaskDeployServiceImpl implements TaskDeployService {

    @Autowired
    TaskDeployDao dao;

    @Autowired
    DeployConfigureDao configureDao;

    /**
     * 部署
     *
     * @param taskId
     * @return
     */
    @Override
    public String deploy(int taskId) {
        Properties props = new Properties();
        List<TaskDeployEntity> taskEntities = dao.findByTaskId(taskId);
        for (TaskDeployEntity entity : taskEntities) {
            String key = entity.getPropertyKey();
            String value = entity.getPropertyValue();
            if(StringUtils.isAnyBlank(key, value)) {
                continue;
            }
            props.put(key, value);
        }
        List<DeployConfigure> configures = configureDao.getSystemProperties();
        for(DeployConfigure configure : configures) {
            String key = configure.getKey();
            String value = configure.getValue();
            if(StringUtils.isAnyBlank(key, value)) {
                continue;
            }
            props.put(key, value);
        }
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
        return "true";
    }
}
