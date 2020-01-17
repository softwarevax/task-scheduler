package org.platform.quartz.deploy.deploy.option;

import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.deploy.listener.DeployListener;
import org.platform.quartz.deploy.git.GitRepository;
import org.platform.quartz.deploy.git.Repository;

/**
 * @author twcao
 * @description git代码获取
 * @project task-scheduler
 * @classname GitFetch
 * @date 2020/1/17 13:19
 */
public class GitFetch implements Fetch {

    private DeployListener listener;

    @Override
    public DeployListener getListener() {
        return this.listener;
    }

    @Override
    public void execute(Context ctx) {
        String uri = ctx.getString("git.repository.http.uri");
        String branch = ctx.getString("git.repository.branch");
        String username = ctx.getString("git.username");
        String password = ctx.getString("git.password");
        String workspace = ctx.getString("deploy.workspace");
        Repository repo = new GitRepository.Builder().uri(uri)
                .branch(branch)
                .userName(username)
                .password(password)
                .workSpace(workspace).build();
        boolean success = repo.fetch();
        String codePath = repo.getSourcePath();
        ctx.put("tmp.code.path", codePath);
    }

    @Override
    public void setListener(DeployListener listener) {
        this.listener = listener;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
