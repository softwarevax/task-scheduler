package org.platform.quartz.deploy.web.service;

/**
 * @author twcao
 * @description 任务部署service
 * @project task-scheduler
 * @classname TaskDeployService
 * @date 2020/1/18 16:06
 */
public interface TaskDeployService {

    /**
     * 部署
     * @param taskId
     * @return
     */
    String deploy(int taskId);
}
