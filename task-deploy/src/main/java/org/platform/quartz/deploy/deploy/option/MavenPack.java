package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.maven.MavenPackage;
import org.platform.quartz.deploy.maven.Package;
import org.platform.quartz.deploy.utils.FileUtils;
import org.platform.quartz.deploy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author twcao
 * @description maven打包
 * @project task-scheduler
 * @classname MavenPack
 * @date 2020/1/17 13:45
 */
public class MavenPack implements Pack {

    public final Logger logger = LoggerFactory.getLogger(MavenPack.class);

    @Override
    public void execute(Context ctx) {
        String codePath = ctx.getString("tmp.code.path");
        if(StringUtils.isBlank(codePath)) {
            logger.error("代码路径不存在, 请确认代码已拉取成功");
        }
        String pomPath = FileUtils.merge(codePath, ctx.getString("deploy.maven.pom.path"));
        // 如果不指定maven地址，可以从环境中获取
        String mavenPath = ctx.getString("deploy.maven.root.path", System.getenv("MAVEN_HOME"));
        String cmd = ctx.getString("deploy.maven.cmd");
        Package pack = new MavenPackage(pomPath, cmd, mavenPath);
        pack.pack();
    }
}
