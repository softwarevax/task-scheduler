package org.platform.quartz.deploy.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.HttpConfig;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.platform.quartz.deploy.domain.DeployConfigureException;
import org.platform.quartz.deploy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author twcao
 * @description git类型仓库
 * @project task-scheduler
 * @classname GitRepository
 * @date 2020/1/15 17:24
 */
public class GitRepository implements Repository {

    public final Logger logger = LoggerFactory.getLogger(GitRepository.class);

    private String userName;

    private String password;

    private String uri;

    private String branch;

    private String projectName;

    private String workSpace;

    private String sourcePath;

    public GitRepository(Builder builder) {
        this.userName = builder.userName;
        this.password = builder.password;
        this.uri = builder.uri;
        this.branch = builder.branch;
        this.workSpace = builder.workSpace;
        this.projectName = builder.projectName;
    }

    /**
     * 用户名
     *
     * @return
     */
    @Override
    public String getUserName() {
        return this.userName;
    }

    /**
     * 密码
     *
     * @return
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 仓库地址
     *
     * @return
     */
    @Override
    public String getUri() {
        return this.uri;
    }

    /**
     * 获取源码的地址 = workSpace + projectName
     * @return
     */
    @Override
    public String getSourcePath() {
        if(!StringUtils.isBlank(sourcePath)) {
            return sourcePath;
        }
        if(StringUtils.isBlank(this.uri)) {
            throw new DeployConfigureException("uri can't be blank");
        }
        if(StringUtils.isBlank(projectName)) {
            this.projectName = StringUtils.substr(this.uri, this.uri.lastIndexOf("/") + 1, this.uri.lastIndexOf("."));
        }
        this.sourcePath = this.workSpace + File.separator + this.projectName;
        return this.sourcePath;
    }

    /**
     * 拉取代码
     * @return
     */
    @Override
    public boolean fetch() {
        try {
            if(!StringUtils.isBlank(this.uri) && this.uri.startsWith("http")) { //git -> http
                CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(this.userName, this.password);
                Git git;
                if (new File(getSourcePath()).exists()) {
                    git = Git.open(new File(getSourcePath()));
                } else {
                    git = Git.cloneRepository().setURI(this.uri).setCredentialsProvider(credentialsProvider).setDirectory(new File(getSourcePath())).call();
                }
                git.getRepository().getConfig().setInt(HttpConfig.HTTP, null, HttpConfig.POST_BUFFER_KEY, 512*1024*1024);
                git.pull().setRemoteBranchName(this.branch).setCredentialsProvider(credentialsProvider).call();
                git.close();
                return true;
            }
            throw new DeployConfigureException("git only support https repository");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public static class Builder {

        private String userName;

        private String password;

        private String uri;

        private String branch;

        private String projectName;

        private String workSpace;

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder branch(String branch) {
            this.branch = branch;
            return this;
        }

        public Builder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder workSpace(String workSpace) {
            this.workSpace = workSpace;
            return this;
        }

        public Repository build() {
            return new GitRepository(this);
        }
    }
}
