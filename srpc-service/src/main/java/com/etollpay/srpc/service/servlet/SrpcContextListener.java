package com.etollpay.srpc.service.servlet;

import com.etollpay.srpc.service.tool.BizRunnerExecutor;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

public class SrpcContextListener extends ContextLoaderListener {
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        BizRunnerExecutor.shutdown();
        super.contextDestroyed(event);
    }
}
