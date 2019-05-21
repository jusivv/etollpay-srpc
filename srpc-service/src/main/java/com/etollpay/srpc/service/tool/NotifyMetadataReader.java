package com.etollpay.srpc.service.tool;

import com.alibaba.fastjson.JSON;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class NotifyMetadataReader implements IMetadataReader {
    private static Logger log = LoggerFactory.getLogger(NotifyMetadataReader.class);

    @Override
    public Metadata read(HttpServletRequest request) {
        try {
            return JSON.parseObject(new BufferedInputStream(request.getInputStream(), 4 * 1024),
                    Charset.forName("UTF-8"), Metadata.class);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
        }
    }
}
