package org.platform.quartz.deploy.web.dao;

import org.platform.quartz.deploy.web.entity.TaskDeployEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author twcao
 * @description 任务实体接口
 * @project task-scheduler
 * @classname TaskDeployDao
 * @date 2020/1/18 15:59
 */
@Repository
public interface TaskDeployDao extends JpaRepository<TaskDeployEntity, Integer> {

    @Query("FROM TaskDeployEntity WHERE taskId = :taskId and enabled = 1")
    List<TaskDeployEntity> findByTaskId(@Param("taskId")int taskId);
}
