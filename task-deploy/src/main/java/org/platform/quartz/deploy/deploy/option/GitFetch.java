package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.deploy.listener.DeployListener;
import org.platform.quartz.deploy.git.GitRepository;
import org.platform.quartz.deploy.git.Repository;
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
    public void execute(Context ctx) {
        String uri = ctx.getString("deploy.git.repository.http.uri");
        String branch = ctx.getString("deploy.git.repository.branch");
        String username = ctx.getString("deploy.git.username");
        String password = ctx.getString("deploy.git.password");
        String workSpace = ctx.getString("deploy.workspace");
        Repository repo = new GitRepository.Builder().uri(uri)
                .branch(branch)
                .userName(username)
                .password(password)
                .workSpace(workSpace).build();
        boolean success = repo.fetch();
        logger.info("代码拉取结果: {}", success);
        String codePath = repo.getSourcePath();
        ctx.put("tmp.code.path", codePath);
    }

    @Override
    public void setListener(DeployListener listener) {
        this.listener = listener;
    }
}
