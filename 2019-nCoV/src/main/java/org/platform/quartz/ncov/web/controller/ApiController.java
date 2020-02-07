package org.platform.quartz.ncov.web.controller;

import org.platform.quartz.ncov.domain.History;
import org.platform.quartz.ncov.web.dao.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.web.controller
 * @Description:
 * @date 2020/2/7 13:08
 */
@RestController
public class ApiController {

    @Autowired
    HistoryDao historyDao;

    @GetMapping("/history")
    public List<History> history() {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "date"));
        return historyDao.findAll(sort);
    }
}
