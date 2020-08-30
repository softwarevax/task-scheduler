package org.platform.quartz.deploy.domain;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.deploy.domain
 * @Description:
 * @date 2020/8/30 9:03
 */
public class NoExpectDeplayClassException extends RuntimeException  {

    public NoExpectDeplayClassException(String msg) {
        super(msg);;
    }
}
