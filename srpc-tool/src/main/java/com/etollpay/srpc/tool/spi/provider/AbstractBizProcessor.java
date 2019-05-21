package com.etollpay.srpc.tool.spi.provider;

import com.etollpay.srpc.tool.ServiceException;
import com.etollpay.srpc.tool.spi.intf.IBizProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.nio.file.Path;

public abstract class AbstractBizProcessor implements IBizProcessor {
    private static Logger log = LoggerFactory.getLogger(AbstractBizProcessor.class);

    @Override
    public String processRequest(Path workspace, Path bizFilePath,
                               String sender, OutputStream resOutput) throws Exception {
        throw new ServiceException("no request processor", 500);
    }

    @Override
    public String processResponse(Path workspace, Path bizFilePath,
                                  String sender, OutputStream resOutput) throws Exception {
        throw new ServiceException("no response processor", 500);
    }

    @Override
    public String processError(Path workspace, Path bizFilePath,
                             String sender, OutputStream resOutput) throws Exception {
        throw new ServiceException("no error processor", 500);
    }
}
