package org.platform.quartz.web.web.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author twcao
 * @description 测试
 * @project task-scheduler
 * @classname HelloAction
 * @date 2020/1/14 9:15
 */
@RestController
public class HelloAction {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

}
