package org.platform.quartz.ncov.web.dao;

import org.platform.quartz.ncov.domain.NcovVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.web.dao
 * @Description:
 * @date 2020/2/6 19:25
 */
@Repository
public interface NcovDao extends JpaRepository<NcovVo, Integer> {

    NcovVo getByDateAndPLocationIdAndAreaName(String date, Integer pLocationId, String areaName);

    NcovVo getByLocationIdAndDateAndAreaName(Integer locationId, String date, String areaName);
}
