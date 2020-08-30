package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.deploy.listener.DeployListener;
import org.platform.quartz.deploy.git.GitRepository;
import org.platform.quartz.deploy.git.Repository;
import org.platform.quartz.deploy.utils.Constants;
import org.platform.quartz.deploy.utils.Constants.Git;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author twcao
 * @description git代码获取
 * @project task-scheduler
 * @classname GitFetch
 * @date 2020/1/17 13:19
 */
public class GitFetch implements Fetch {

    public final Logger logger = LoggerFactory.getLogger(GitFetch.class);

    private DeployListener listener;

    @Override
    public DeployListener getListener() {
        return this.listener;
    }

    @Override
    public boolean execute(Context ctx) {
        String uri = ctx.getString(Git.GIT_REPO_HTTP_URI);
        String branch = ctx.getString(Git.GIT_REPO_BRANCH);
        String username = ctx.getString(Git.GIT_REPO_USERNAME);
        String password = ctx.getString(Git.GIT_REPO_PWD);
        String workSpace = ctx.getString(Constants.DEPLOY_WORK_SPACE);
        Repository repo = new GitRepository.Builder().uri(uri)
                .branch(branch)
                .userName(username)
                .password(password)
                .workSpace(workSpace).build();
        boolean result = repo.fetch();
        String codePath = repo.getSourcePath();
        ctx.put(Constants.TMP_PROJECT_SOURCE_CODE_PATH, codePath);
        return result;
    }

    @Override
    public void setListener(DeployListener listener) {
        this.listener = listener;
    }
}
