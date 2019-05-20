package com.etollpay.srpc.service.servlet;

import com.etollpay.srpc.standard.basic.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageAsyncResponseInvoker implements IAsyncResponseInvoker {
    private static Logger log = LoggerFactory.getLogger(MessageAsyncResponseInvoker.class);

    @Override
    public void success(Metadata resMetadata) {
        // TODO
    }

    @Override
    public void error(Throwable t, Metadata metadata) {
        // TODO
    }
}
