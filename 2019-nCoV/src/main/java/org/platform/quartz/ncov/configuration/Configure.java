package org.platform.quartz.ncov.configuration;

import org.platform.quartz.ncov.web.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.configuration
 * @Description:
 * @date 2020/2/7 14:51
 */
@Configuration
public class Configure {

    @Autowired
    private RequestListener requestListener;

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(requestListener);
        return servletListenerRegistrationBean;
    }

    @Bean
    public HistoryService historyService(WebSocketServer webSocketServer) {
        HistoryService historyService = new HistoryService();
        historyService.register(webSocketServer);
        return historyService;
    }
}
