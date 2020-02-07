package org.platform.quartz.ncov.web.service;

import org.platform.quartz.ncov.domain.NcovVo;
import org.platform.quartz.ncov.web.dao.NcovDao;
import org.platform.quartz.utils.langs.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.web.service
 * @Description:
 * @date 2020/2/6 19:25
 */
@Service
public class NcovService {

    @Autowired
    private NcovDao ncovDao;

    public void saveOrUpdate(List<NcovVo> ncovVos) {
        if(CollectionUtils.isEmpty(ncovVos)) {
            return;
        }
        List<NcovVo> vos = new ArrayList<>(ncovVos.size() + 1);
        for(NcovVo vo : ncovVos) {
            NcovVo tmp = null;
            if(vo.getPLocationId() == null) {
                tmp = ncovDao.getByLocationIdAndDateAndAreaName(vo.getLocationId(), vo.getDate(), vo.getAreaName());
            } else {
                tmp = ncovDao.getByDateAndPLocationIdAndAreaName(vo.getDate(), vo.getPLocationId(), vo.getAreaName());
            }
            if(tmp == null) {
                vos.add(vo);
            } else {
                Integer id = tmp.getId();
                ObjectUtils.merge(vo, tmp);
                tmp.setId(id);
                vos.add(tmp);
            }
        }
        ncovDao.saveAll(vos);
    }
}
