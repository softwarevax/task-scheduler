package org.platform.quartz.deploy.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.platform.quartz.deploy.deploy.AbstractDeploy;
import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.deploy.Deploy;
import org.platform.quartz.deploy.deploy.DeployFactory;
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
@Slf4j
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
        // user properties will override system properties
        List<TaskDeployEntity> taskEntities = dao.findByTaskId(taskId);
        for (TaskDeployEntity entity : taskEntities) {
            String key = entity.getPropertyKey();
            String value = entity.getPropertyValue();
            if(StringUtils.isAnyBlank(key, value, entity.getFqn())) {
                continue;
            }
            try {
                Class<?> clazz = Class.forName(entity.getFqn());
                if(!deploys.contains(clazz)) {
                    deploys.add((Class<? extends Deploy>) clazz);
                }
                props.put(key, value);
            } catch (ClassNotFoundException cnfe) {
                log.error(cnfe.getMessage(), cnfe);
            } catch (ClassCastException ccef) {
                log.error(ccef.getMessage(), ccef);
            }
        }
        AbstractDeploy deploy = DeployFactory.getInstance(deploys);
        Context ctx = new Context();
        ctx.putAll(PropertyUtils.toMap(props));
        deploy.execute(ctx);
        return "true";
    }
}
