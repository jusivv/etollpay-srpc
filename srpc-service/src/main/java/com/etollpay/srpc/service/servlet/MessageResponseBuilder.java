package com.etollpay.srpc.service.servlet;

import com.etollpay.srpc.service.util.FilePath;
import com.etollpay.srpc.service.util.HeaderSetter;
import com.etollpay.srpc.service.util.ServiceException;
import com.etollpay.srpc.service.util.SysConfig;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.standard.MetadataHelper;
import org.bouncycastle.util.io.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MessageResponseBuilder implements IResponseBuilder {
    private static Logger log = LoggerFactory.getLogger(MessageResponseBuilder.class);

    @Override
    public void success(Metadata resMetadata, HttpServletResponse response, int statusCode) throws IOException {
        MetadataHelper.setHeaders(resMetadata, new HeaderSetter(response));
        response.setStatus(statusCode);
        if (resMetadata.isHasFile()) {
            response.setContentType("application/octet-stream");
            Path resArchivePath =
                    Paths.get(FilePath.getOutboundPath(resMetadata),
                            MetadataHelper.getBizFileName(resMetadata));
            if (Files.exists(resArchivePath)) {
                InputStream inputStream = new BufferedInputStream(Files.newInputStream(resArchivePath),
                        8 * 1024);
                try {
                    Streams.pipeAll(inputStream, response.getOutputStream());
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        } else {
            response.setContentType("text/plain; charset=utf-8");
        }
    }

    @Override
    public void success(Metadata resMetadata, HttpServletResponse response) throws IOException {
        success(resMetadata, response, HttpServletResponse.SC_OK);
    }

    @Override
    public void error(Throwable t, Metadata metadata, HttpServletResponse response, int statusCode) {
        ServiceException serviceException = t instanceof ServiceException ? (ServiceException)t :
                new ServiceException(IntfError.UNKNOWN_ERROR, t.getLocalizedMessage());
        response.setStatus(statusCode);
        response.setContentType("application/json; charset=utf-8");
        Metadata resMetadata = null;
        if (metadata != null) {
            resMetadata = metadata.response();
        } else {
            resMetadata = MetadataHelper.createMetadata(null, null, null);
        }
        resMetadata.setReqType(Metadata.REQUEST_TYPE_ERR);
        resMetadata.setHasFile(false);
        resMetadata.setFileEncryption(null);
        resMetadata.setErrorCode(serviceException.getErrorCode());
        resMetadata.setMac(MetadataHelper.getMac(resMetadata,
                Common.decodeBase64(SysConfig.getString(
                        "secretKey." + resMetadata.getRecipient()))));
        MetadataHelper.setHeaders(resMetadata, new HeaderSetter(response));
        try {
            response.getWriter().write(serviceException.toJSON());
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void error(Throwable t, Metadata metadata, HttpServletResponse response) {
        error(t, metadata, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
