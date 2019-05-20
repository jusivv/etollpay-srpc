package com.etollpay.srpc.service.servlet;

import com.etollpay.srpc.service.tool.BizRunner;
import com.etollpay.srpc.service.tool.BizRunnerExecutor;
import com.etollpay.srpc.service.tool.NotifyBizFileReader;
import com.etollpay.srpc.service.tool.NotifyMetadataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "notifyServlet", urlPatterns = "/notify/v1.1", asyncSupported = true)
public class NotifyServlet extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(NotifyServlet.class);


    @Override
    public void init() throws ServletException {
        super.init();
        log.debug("loading notify servlet...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        BizRunnerExecutor.execute(new BizRunner(asyncContext, new NotifyMetadataReader(), new NotifyBizFileReader(),
                new NotifyResponseBuilder(), new NotifyAsyncResponseInvoker()));

    }
}
