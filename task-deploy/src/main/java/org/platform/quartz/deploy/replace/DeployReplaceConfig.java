package org.platform.quartz.deploy.replace;

/**
 * @author twcao
 * @description 替换配置
 * @project task-scheduler
 * @classname DeployReplaceConfig
 * @date 2020/1/16 9:13
 */
public class DeployReplaceConfig implements ReplaceStep {

    private String filePath;

    private String old;

    private String target;

    private String filterFileName;

    private int order;

    private String codePath;

    public DeployReplaceConfig(String codePath, String filePath, String old, String target, String filterFileName, int order) {
        this.codePath = codePath;
        this.filePath = filePath;
        this.old = old;
        this.target = target;
        this.filterFileName = filterFileName;
        this.order = order;
    }

    @Override
    public String getFilePath() {
        return this.filePath;
    }

    @Override
    public String getOld() {
        return this.old;
    }

    @Override
    public String getTarget() {
        return this.target;
    }

    @Override
    public String getFilterFileName() {
        return this.filterFileName;
    }

    @Override
    public String getCodePath() {
        return this.codePath;
    }

    @Override
    public int getOrder() {
        return this.order;
    }
}
