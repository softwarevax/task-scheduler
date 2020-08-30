package org.platform.quartz.deploy.web.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author twcao
 * @description 任务部署实体
 * @project task-scheduler
 * @classname TaskDeployEntity
 * @date 2020/1/18 16:00
 */
@Entity
@Data
@Table(name = "qrtz_deploy_task")
public class TaskDeployEntity {

    @Id
    private Integer id;

    private Integer taskId;

    private String taskName;

    private Integer optionId;

    private String fqn;

    private String propertyKey;

    private String propertyValue;

    private String remark;

    private String userId;

    private String enabled;
}
