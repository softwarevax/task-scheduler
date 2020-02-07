package org.platform.quartz.ncov.configuration;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.configuration
 * @Description:
 * @date 2020/2/7 15:50
 */
@Component
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre)  {
        //将所有request请求都携带上httpSession
        ((HttpServletRequest) sre.getServletRequest()).getSession();

    }
    public RequestListener() {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent arg0)  {
    }
}
