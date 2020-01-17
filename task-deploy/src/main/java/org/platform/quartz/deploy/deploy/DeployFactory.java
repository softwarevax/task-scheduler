package org.platform.quartz.deploy.deploy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author twcao
 * @description 创建部署步骤
 * @project task-scheduler
 * @classname DeployFactory
 * @date 2020/1/17 14:38
 */
public class DeployFactory {

    public static AbstractDeploy getInstance(List<Class<? extends Deploy>> deploys) {
        AbstractDeploy deploy = new AbstractDeploy() {
            @Override
            public int getOrder() {
                return 0;
            }
        };
        List<Deploy> deployList = new ArrayList<>();
        try {
            for(Class clazz : deploys) {
                if(clazz == null) {
                    continue;
                }
                Deploy d = (Deploy) clazz.newInstance();
                deployList.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        deploy.addDeploy(deployList);
        return deploy;
    }
}
