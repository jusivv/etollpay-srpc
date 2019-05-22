package com.etollpay.srpc.service.processor.checker;

import com.etollpay.srpc.service.processor.IPreProcessor;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Assert;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class MetadataChecker implements IPreProcessor {
    @Override
    public void process(Metadata metadata, HttpServletRequest request) throws ServiceException {
        Assert.is(!Common.inArray(metadata.getReqType(),
                new String[]{Metadata.REQUEST_TYPE_REQ, Metadata.REQUEST_TYPE_RES, Metadata.REQUEST_TYPE_ERR}),
                IntfError.ILLEGAL_REQTYP);
    }

    @Override
    public boolean accept(String tag) {
        return true;
    }
}
