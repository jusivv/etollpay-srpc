package com.etollpay.srpc.service.servlet;


import com.etollpay.srpc.standard.basic.Metadata;

public class NotifyAsyncResponseInvoker implements IAsyncResponseInvoker {
    @Override
    public void success(Metadata resMetadata) {
        // TODO 搬运数据 - invoke

    }

    @Override
    public void error(Throwable t, Metadata metadata) {
        // TODO 构造异常响应 - invoke

    }
}
