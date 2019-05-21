package com.etollpay.srpc.service.tool;

import com.etollpay.srpc.service.util.FilePath;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Assert;
import com.etollpay.srpc.tool.ServiceException;
import com.etollpay.srpc.tool.standard.MetadataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NotifyBizFileReader implements IBizFileReader {
    private static Logger log = LoggerFactory.getLogger(NotifyBizFileReader.class);

    public String read(Metadata metadata, HttpServletRequest request) {
        return read(metadata, request, MetadataHelper.getDefaultHashAlgorithm());
    }

    public String read(Metadata metadata, HttpServletRequest request, String hashAlgorithm) {
        if (metadata.isHasFile()) {
            String bizFileName = MetadataHelper.getBizFileName(metadata);
            Path archivePath = Paths.get(FilePath.getInboundPath(metadata, true), bizFileName);
            Assert.is(Files.exists(archivePath), IntfError.DUPLICATE_BIZ_FILE,
                    MetadataHelper.getBizFileName(metadata));
            Path tempPath = Paths.get(FilePath.getFtpInboundPath(metadata), bizFileName);
            log.debug("upload file: {}", tempPath.toString());
            Assert.is(!Files.exists(tempPath), IntfError.FILE_NOT_FOUND, bizFileName);
            try {
                Files.move(tempPath, archivePath);
            } catch (IOException e) {
                log.error(e.getLocalizedMessage(), e);
                throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
            }
        }
        return null;
    }
}
