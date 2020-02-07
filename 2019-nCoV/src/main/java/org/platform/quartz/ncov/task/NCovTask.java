package org.platform.quartz.ncov.task;

import com.alibaba.fastjson.JSON;
import org.platform.quartz.ncov.domain.*;
import org.platform.quartz.ncov.web.service.HistoryService;
import org.platform.quartz.ncov.web.service.NcovService;
import org.platform.quartz.utils.code.UniCodeUtils;
import org.platform.quartz.utils.date.DateUitls;
import org.platform.quartz.utils.net.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.task
 * @Description:
 * @date 2020/2/6 18:13
 */
@Component
public class NCovTask {

    @Autowired
    HistoryService historyService;

    @Autowired
    NcovService ncovService;

    @Value("${ncov.api: }")
    private String ncovApi;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void schedule() {
        String date = DateUitls.getDate();
        String dateTime = DateUitls.getDateTime();
        String content = HttpUtils.sendGet(ncovApi);
        content = UniCodeUtils.unicodeToUtf8(content);
        ResultDto dto = JSON.parseObject(content, ResultDto.class);
        List<NcovVo> ncovVos = new ArrayList<>();
        NCoV data = dto.getData();
        List<History> histories = data.getHistory();
        History today = new History();
        today.setConfirmedNum(data.getDiagnosed());
        today.setCuresNum(data.getCured());
        today.setDate(date);
        today.setDeathsNum(data.getDeath());
        today.setSuspectedNum(data.getSuspect());
        today.setSuspectedIncr(data.getSuspectIncr());
        histories.add(today);
        historyService.saveOrUpdate(histories);
        List<Area> areas = data.getArea();
        for (Area area : areas) {
            NcovVo vo = new NcovVo();
            vo.setAreaName(area.getProvinceName());
            vo.setComment(area.getComment());
            vo.setConfirmedCount(area.getConfirmedCount());
            vo.setCuredCount(area.getCuredCount());
            vo.setDeadCount(area.getDeadCount());
            vo.setSuspectedCount(area.getSuspectedCount());
            vo.setDate(date);
            vo.setUpdateTime(dateTime);
            Integer locationId = area.getLocationId();
            vo.setLocationId(locationId);
            ncovVos.add(vo);
            List<City> cities = area.getCities();
            for(City city : cities) {
                NcovVo cv = new NcovVo();
                cv.setAreaName(city.getCityName());
                cv.setConfirmedCount(city.getConfirmedCount());
                cv.setCuredCount(city.getCuredCount());
                cv.setDeadCount(city.getDeadCount());
                cv.setSuspectedCount(city.getSuspectedCount());
                cv.setDate(date);
                cv.setPLocationId(locationId);
                cv.setUpdateTime(dateTime);
                ncovVos.add(cv);
            }
        }
        ncovService.saveOrUpdate(ncovVos);
    }
}
