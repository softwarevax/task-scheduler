package org.platform.quartz.ncov.configuration;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.configuration
 * @Description:
 * @date 2020/2/7 16:08
 */
public interface SenderListener {

    /**
     * 更新数据
     * @param msg
     */
    void update(String msg);
}
