org.quartz.Scheduler:
shutdonw(boolean waitForJobsToComplete); 是否等当前任务运行完再停止



Job属性:
```$xslt
1、Job 的易失性: 一个易失性的 Job 是在程序关闭之后不会被持久化。一个 Job 是通过调用 JobDetail 的 setVolatility(true) 被设置为易失性的
2、Job 持久性:
3、Job 的可恢复性:Scheduler 重启之后可恢复的 Job 还会再次被执行
```

系统自带的Job:
```$xslt
org.quartz.jobs.FileScanJob 检查某个指定文件是否变化，并在文
件被改变时通知到相应监听器的 Job 
org.quartz.jobs.FileScanListener 在文件被修改后通知 FileScanJob 的监听器  
org.quartz.jobs.NativeJob 用来执行本地程序(如 windows 下 .exe 文件) 的 Job 
org.quartz.jobs.NoOpJob 什么也不做，但用来测试监听器不是很有用的。
一些用户甚至仅仅用它来导致一个监听器的运行 
org.quartz.jobs.ee.mail.SendMailJob 使用 JavaMail API 发送 e-mail 的 Job 
org.quartz.jobs.ee.jmx.JMXInvokerJob 调用 JMX bean 上的方法的 Job 
org.quartz.jobs.ee.ejb.EJBInvokerJob 用来调用 EJB 上方法的 Job 

```

默认的配置
```$xslt
# Default Properties file for use by StdSchedulerFactory  
# to create a Quartz Scheduler Instance, if a different  
# properties file is not explicitly specified.   
  
org.quartz.scheduler.instanceName = DefaultQuartzScheduler   
org.quartz.scheduler.rmi.export = false  
org.quartz.scheduler.rmi.proxy = false  
org.quartz.scheduler.wrapJobExecutionInUserTransaction = false  
org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool   
org.quartz.threadPool.threadCount = 10   
org.quartz.threadPool.threadPriority = 5   
org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread = true  
  
org.quartz.jobStore.misfireThreshold = 60000   
  
org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore  

```

修改数据库表的前缀:org.quartz.jobStore.tablePrefix = SCHEDULER2_
动态切换cron: Scheduler.rescheduleJob
