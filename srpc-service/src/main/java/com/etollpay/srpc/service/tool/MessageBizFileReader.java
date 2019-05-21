package com.etollpay.srpc.service.tool;

import com.etollpay.srpc.service.util.FilePath;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Assert;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.ServiceException;
import com.etollpay.srpc.tool.standard.MetadataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

public class MessageBizFileReader implements IBizFileReader {
    private static Logger log = LoggerFactory.getLogger(MessageBizFileReader.class);


    @Override
    public String read(Metadata metadata, HttpServletRequest request) {
        return read(metadata, request, MetadataHelper.getDefaultHashAlgorithm());
    }

    @Override
    public String read(Metadata metadata, HttpServletRequest request, String hashAlgorithm) {
        if (metadata.isHasFile()) {
            Path archivePath = Paths.get(FilePath.getInboundPath(metadata, true),
                    MetadataHelper.getBizFileName(metadata));
            Assert.is(Files.exists(archivePath), IntfError.DUPLICATE_BIZ_FILE,
                    MetadataHelper.getBizFileName(metadata));
            try {
                Collection<Part> parts = request.getParts();
                if (parts != null && parts.iterator().hasNext()) {
                    BufferedOutputStream archiveOutput = new BufferedOutputStream(
                            Files.newOutputStream(archivePath), 8 * 1024);
                    try {
                        return Common.copyStreamWithHashCode(parts.iterator().next().getInputStream(), hashAlgorithm,
                                archiveOutput);
                    } finally {
                        if (archiveOutput != null) {
                            archiveOutput.close();
                        }
                    }
                }
            } catch (IOException e) {
                log.error(e.getLocalizedMessage(), e);
                throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
            } catch (ServletException e) {
                log.error(e.getLocalizedMessage(), e);
                throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
            } catch (NoSuchAlgorithmException e) {
                log.error(e.getLocalizedMessage(), e);
                throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
            }
        }
        return null;
    }
}
