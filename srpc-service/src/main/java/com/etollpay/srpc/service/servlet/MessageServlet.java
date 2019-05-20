package com.etollpay.srpc.service.servlet;

import com.etollpay.srpc.service.tool.BizRunner;
import com.etollpay.srpc.service.tool.BizRunnerExecutor;
import com.etollpay.srpc.service.tool.MessageBizFileReader;
import com.etollpay.srpc.service.tool.MessageMetadataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "messageServlet", urlPatterns = "/message/v1.1", asyncSupported = true)
@MultipartConfig
public class MessageServlet extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(MessageServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        log.debug("loading message servlet...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(60 * 1000);
        BizRunnerExecutor.execute(new BizRunner(asyncContext, new MessageMetadataReader(), new MessageBizFileReader(),
                new MessageResponseBuilder(), new MessageAsyncResponseInvoker()));


    }

}
