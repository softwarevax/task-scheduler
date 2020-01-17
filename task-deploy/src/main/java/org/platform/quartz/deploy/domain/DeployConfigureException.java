package org.platform.quartz.deploy.domain;

/**
 * @author twcao
 * @description 部署配置异常
 * @project task-scheduler
 * @classname DeployConfigureException
 * @date 2020/1/15 18:08
 */
public class DeployConfigureException extends RuntimeException  {

    public DeployConfigureException(String msg) {
        super(msg);;
    }
}
