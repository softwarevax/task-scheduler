package org.platform.quartz.deploy.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.HttpConfig;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;

/**
 * @author twcao
 * @description git代码拉取
 * @project task-scheduler
 * @classname GitHelper
 * @date 2020/1/13 14:52
 */
public class GitHelper {

    public static void download(String httpUri, String username, String password, String localPath, String branch) {
        try {
            CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(username, password);
            Git git;
            if (new File(localPath).exists()) {
                git = Git.open(new File(localPath));
            } else {
                git = Git.cloneRepository().setCredentialsProvider(credentialsProvider).setURI(httpUri).setDirectory(new File(localPath)).call();
            }
            git.getRepository().getConfig().setInt(HttpConfig.HTTP, null, HttpConfig.POST_BUFFER_KEY, 512*1024*1024);
            git.pull().setRemote(branch).setCredentialsProvider(credentialsProvider).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
