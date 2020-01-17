package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.maven.CompressHelper;
import org.platform.quartz.deploy.maven.MavenPackage;
import org.platform.quartz.deploy.maven.Package;
import org.platform.quartz.deploy.utils.FileUtils;

import java.io.File;

/**
 * @author twcao
 * @description maven打包
 * @project task-scheduler
 * @classname MavenPack
 * @date 2020/1/17 13:45
 */
public class MavenPack implements Pack {

    @Override
    public void execute(Context ctx) {
        String codePath = ctx.getString("tmp.code.path");
        String pomPath = FileUtils.merge(codePath, ctx.getString("deploy.maven.pom.path"));
        // 如果不指定maven地址，可以从环境中获取
        String mavenPath = ctx.getString("deploy.maven.path", System.getenv("MAVEN_HOME"));
        String cmd = ctx.getString("deploy.maven.cmd");
        Package pack = new MavenPackage(pomPath, cmd, mavenPath);
        pack.pack();

        CompressHelper.compress(codePath + "." + ctx.getString("deploy.file.compress.suffix"), new File(codePath + "\\bin").listFiles());
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
