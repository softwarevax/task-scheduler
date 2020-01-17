package org.platform.quartz.core;

import org.quartz.*;

import java.util.Date;

/**
 * @author twcao
 * @description example
 * @project task-scheduler
 * @classname MyJob
 * @date 2020/1/10 9:55
 */
public class MyJob implements Job, JobListener {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            System.out.println("定时器任务执行" + new Date(System.currentTimeMillis()));
            JobDataMap map=context.getMergedJobDataMap();
            System.out.println("参数值"+map.get("uname"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "MyJob Listener";
    }

    /**
     * Scheduler 在 JobDetail 将要被执行时调用这个方法。
     * @param context
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("MyJob will be starting");
    }

    /**
     * Scheduler 在 JobDetail 即将被执行，但又被 TriggerListener 否决了时调用这个方法。
     * @param context
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("MyJob will not execute");
    }

    /**
     * Scheduler 在 JobDetail 被执行之后调用这个方法。
     * @param context
     * @param jobException
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("MyJob has finished");
    }
}