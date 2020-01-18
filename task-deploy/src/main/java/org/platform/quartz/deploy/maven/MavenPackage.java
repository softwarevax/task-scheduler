package org.platform.quartz.deploy.maven;

import org.apache.maven.shared.invoker.*;
import org.platform.quartz.deploy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author twcao
 * @description maven打包
 * @project task-scheduler
 * @classname MavenPackage
 * @date 2020/1/16 9:53
 */
public class MavenPackage implements Package {

    public static final Logger logger = LoggerFactory.getLogger(MavenPackage.class);

    private String pomPath;

    private String mavenPath;

    private String cmd;

    public MavenPackage(String pomPath, String cmd, String mavenPath) {
        this.pomPath = pomPath;
        this.cmd = cmd;
        if(StringUtils.isBlank(mavenPath)) {
            mavenPath = System.getenv("MAVEN_HOME");
        }
        this.mavenPath = mavenPath;
    }

    public MavenPackage(String pomPath, String cmd) {
        this(pomPath, cmd, null);
    }

    /**
     * 打包
     */
    @Override
    public void pack() {
        if(StringUtils.isAnyBlank(pomPath, cmd, mavenPath)) {
            return;
        }
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(pomPath));
        if(!StringUtils.isBlank(cmd)) {
            if(cmd.startsWith("mvn")) {
                cmd = cmd.replace("mvn", "");
            }
            List<String> goals = Arrays.asList(cmd.split(" ")).stream().map(cs -> cs.trim()).filter(cs -> !StringUtils.isBlank(cs)).collect(Collectors.toList());
            request.setGoals(goals);
        }
        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(mavenPath));
        try {
            invoker.execute(request);
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }
}
