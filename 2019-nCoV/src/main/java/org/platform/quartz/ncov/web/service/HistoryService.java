package org.platform.quartz.ncov.web.service;

import org.platform.quartz.ncov.configuration.SenderListener;
import org.platform.quartz.ncov.configuration.Subject;
import org.platform.quartz.ncov.domain.History;
import org.platform.quartz.ncov.web.dao.HistoryDao;
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
        for (History h : histories) {
            String date = h.getDate();
            History history  =  historyDao.getByDate(date);
            if(history != null) {
                h.setId(history.getId());
            }
            if(history == null || !history.equals(h)) {
                notify(h.toString());
                historyDao.saveAndFlush(h);
            }
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
