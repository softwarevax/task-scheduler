package org.platform.quartz.deploy.web.controller;

import org.platform.quartz.deploy.web.service.TaskDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author twcao
 * @description 任务部署接口
 * @project task-scheduler
 * @classname TaskDeployController
 * @date 2020/1/18 16:03
 */
@RestController
public class TaskDeployController {

    @Autowired
    TaskDeployService service;

    @GetMapping("/deploy/{taskId}")
    public String start(@PathVariable String taskId) {
        return service.deploy(Integer.valueOf(taskId));
    }
}
