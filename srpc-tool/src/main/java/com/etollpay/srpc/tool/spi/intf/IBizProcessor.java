package com.etollpay.srpc.tool.spi.intf;


import java.io.OutputStream;
import java.nio.file.Path;

public interface IBizProcessor extends IProviderSelector {
    String processRequest(Path workspace, Path bizFilePath,
                          String sender, OutputStream resOutput) throws Exception;

    String processResponse(Path workspace, Path bizFilePath,
                           String sender, OutputStream resOutput) throws Exception;

    String processError(Path workspace, Path bizFilePath,
                        String sender, OutputStream resOutput) throws Exception;
}
