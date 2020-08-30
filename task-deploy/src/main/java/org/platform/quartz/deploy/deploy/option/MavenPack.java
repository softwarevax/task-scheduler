package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.maven.MavenPackage;
import org.platform.quartz.deploy.maven.Package;
import org.platform.quartz.deploy.utils.Constants;
import org.platform.quartz.deploy.utils.Constants.Maven;
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
    public boolean execute(Context ctx) {
        String codePath = ctx.getString(Constants.TMP_PROJECT_SOURCE_CODE_PATH);
        if(StringUtils.isBlank(codePath)) {
            logger.error("the code path does not exist. Please confirm that the code has pulled successfully");
            return false;
        }
        String pomPath = FileUtils.merge(codePath, ctx.getString(Maven.POM_PATH));
        // If not specify a Maven address, get it from the environment
        String mavenPath = ctx.getString(Maven.MAVEN_PATH, System.getenv("MAVEN_HOME"));
        String cmd = ctx.getString(Maven.MAVEN_COMMAND);
        Package pack = new MavenPackage(pomPath, cmd, mavenPath);
        return pack.pack();
    }
}
