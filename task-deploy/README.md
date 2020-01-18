1、新增一个基于git https方式的自动化部署: org.platform.quartz.deploy.Application
2、linux远程连接支持命令交互
3、运行流程改造
4、部署的变量以"deploy."开头, 中间的变量以"tmp."开头，从哪个操作开始, 就接哪个操作，如"deploy.replace.."
5、完成自动化部署流程, 但不通用, 仍需完善 