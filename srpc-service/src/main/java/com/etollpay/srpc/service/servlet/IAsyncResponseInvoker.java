package com.etollpay.srpc.service.servlet;


import com.etollpay.srpc.standard.basic.Metadata;

public interface IAsyncResponseInvoker {
    void success(Metadata resMetadata);

    void error(Throwable t, Metadata metadata);
}
