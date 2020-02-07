package org.platform.quartz.ncov.web.dao;

import org.platform.quartz.ncov.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.web.dao
 * @Description:
 * @date 2020/2/6 18:48
 */
@Repository
public interface HistoryDao extends JpaRepository<History, Integer> {

    History getByDate(String date);

    @Query(value = "select date from History")
    List<String> getDates();
}
