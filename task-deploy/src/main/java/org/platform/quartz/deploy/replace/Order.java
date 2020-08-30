package org.platform.quartz.deploy.replace;

/**
 * @author twcao
 * @description 顺序接口，越小越先执行
 * @project task-scheduler
 * @classname Order
 * @date 2020/1/16 9:06
 */
public interface Order {

    int getOrder();

    default void setOrder() {}
}
