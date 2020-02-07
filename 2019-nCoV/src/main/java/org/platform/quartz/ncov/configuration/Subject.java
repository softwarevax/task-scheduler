package org.platform.quartz.ncov.configuration;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.configuration
 * @Description:
 * @date 2020/2/7 16:01
 */
public interface Subject {

    /**
     * 添加观察者
     * @param o
     */
    void register(SenderListener o);

    /**
     * 删除观察者
     * @param o
     */
    void remove(SenderListener o);

    /**
     * 通知
     */
    void notify(String msg);
}
