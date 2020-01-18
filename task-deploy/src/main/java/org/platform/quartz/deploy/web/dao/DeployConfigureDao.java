package org.platform.quartz.deploy.web.dao;

import org.platform.quartz.deploy.web.entity.DeployConfigure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author twcao
 * @description 部署配置
 * @project task-scheduler
 * @classname DeployConfigureDao
 * @date 2020/1/18 18:19
 */
@Repository
public interface DeployConfigureDao extends JpaRepository<DeployConfigure, Integer> {

    @Query(value = "from DeployConfigure a where a.type = 'property'")
    List<DeployConfigure> getSystemProperties();
}
