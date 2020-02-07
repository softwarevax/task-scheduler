package org.platform.quartz.ncov.web.service;

import org.platform.quartz.ncov.configuration.SenderListener;
import org.platform.quartz.ncov.configuration.Subject;
import org.platform.quartz.ncov.domain.History;
import org.platform.quartz.ncov.web.dao.HistoryDao;
import org.platform.quartz.utils.date.DateUitls;
import org.platform.quartz.utils.langs.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.web.service
 * @Description:
 * @date 2020/2/6 18:48
 */
public class HistoryService implements Subject {

    @Autowired
    private HistoryDao historyDao;

    private List<SenderListener> senders = new ArrayList<>();

    public void saveOrUpdate(List<History> histories) {
        if(CollectionUtils.isEmpty(histories)) {
            return;
        }
        List<String> dates = historyDao.getDates();
        for (History h : histories) {
            if(dates.contains(h.getDate()) && !StringUtils.equals(DateUitls.getDate(), h.getDate())) {
                // 如果日期存在，且不是今天，表明是历史数据, 历史数据不会修改
                continue;
            }
            if(StringUtils.equals(DateUitls.getDate(), h.getDate()))  {
                // 如果是今天
                History today  =  historyDao.getByDate(DateUitls.getDate());
                h.setId(today.getId());
                if(!today.equals(h)) {
                    notify(h.toString());
                }
            }
            historyDao.saveAndFlush(h);
        }
    }

    @Override
    public void register(SenderListener o) {
        if(senders.contains(o))  {
            return;
        }
        senders.add(o);
    }

    @Override
    public void remove(SenderListener o) {
        if(senders.contains(o))  {
            senders.remove(o);

        }
    }

    @Override
    public void notify(String msg) {
        for(SenderListener sender : senders) {
            sender.update(msg);
        }
    }
}
