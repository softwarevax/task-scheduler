package org.platform.quartz.deploy.deploy;

import lombok.extern.slf4j.Slf4j;
import org.platform.quartz.deploy.deploy.listener.DeployListener;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author twcao
 * @description 抽象部署类
 * @project task-scheduler
 * @classname AbstractDeploy
 * @date 2020/1/17 11:43
 */
@Slf4j
public abstract class AbstractDeploy implements Deploy {

    protected Context ctx;

    protected List<Deploy> deploys;

    public AbstractDeploy() {
        this.deploys = new ArrayList<>();
    }

    public AbstractDeploy(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public boolean execute(Context ctx) {
        if(CollectionUtils.isEmpty(deploys)) {
            return false;
        }
        deploys.sort(Comparator.comparing(Deploy::getOrder));
        for(Deploy deploy : deploys) {
            DeployListener listener = deploy.getListener();
            if(listener != null) {
                listener.before(ctx);
                deploy.execute(ctx);
                listener.after(ctx);
                continue;
            }
            log.info("{}, result = {}", deploy.getClass(), deploy.execute(ctx));
        }
        return true;
    }

    public void addDeploy(List<Deploy> deploys) {
        this.deploys.addAll(deploys);
    }
}
