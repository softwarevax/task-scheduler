package org.platform.quartz.ncov.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.web.controller
 * @Description:
 * @date 2020/2/7 13:00
 */
@Controller
public class PageViewController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
