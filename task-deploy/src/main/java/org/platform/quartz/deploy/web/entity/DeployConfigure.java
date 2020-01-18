package org.platform.quartz.deploy.web.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author twcao
 * @description 部署系统配置
 * @project task-scheduler
 * @classname DeployConfigure
 * @date 2020/1/18 18:17
 */
@Entity
@Data
@Table(name = "qrtz_deploy_configure")
public class DeployConfigure {

    @Id
    private Integer id;

    private String name;

    private String key;

    private String value;

    private String type;

    private String remark;
}
