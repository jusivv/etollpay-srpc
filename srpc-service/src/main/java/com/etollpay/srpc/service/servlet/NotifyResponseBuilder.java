package com.etollpay.srpc.service.servlet;

import com.alibaba.fastjson.JSON;
import com.etollpay.srpc.service.util.FilePath;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.standard.basic.NotifyError;
import com.etollpay.srpc.tool.Assert;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.ServiceException;
import com.etollpay.srpc.tool.SysConfig;
import com.etollpay.srpc.tool.standard.MetadataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NotifyResponseBuilder implements IResponseBuilder {
    private static Logger log = LoggerFactory.getLogger(NotifyResponseBuilder.class);

    @Override
    public void success(Metadata resMetadata, HttpServletResponse response) throws IOException {
        success(resMetadata, response, HttpServletResponse.SC_OK);
    }

    @Override
    public void success(Metadata resMetadata, HttpServletResponse response, int statusCode) throws IOException {
        response.setStatus(statusCode);
        if (resMetadata.isHasFile()) {
            // move file
            String bizFileName = MetadataHelper.getBizFileName(resMetadata);
            Path resArchivePath = Paths.get(FilePath.getOutboundPath(resMetadata), bizFileName);
            Assert.is(!Files.exists(resArchivePath), IntfError.LOST_BIZ_FILE, bizFileName);
            Files.copy(resArchivePath, Paths.get(FilePath.getFtpOutboundPath(resMetadata, true), bizFileName),
                    StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
        }
        // response metadata
        response.setContentType("application/json; charset=utf-8");
        JSON.writeJSONString(response.getOutputStream(), Charset.forName("UTF-8"), resMetadata);
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
        try {
            JSON.writeJSONString(response.getOutputStream(), Charset.forName("UTF-8"),
                    new NotifyError(resMetadata, serviceException.getLocalizedMessage()));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void error(Throwable t, Metadata metadata, HttpServletResponse response) {
        error(t, metadata, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
