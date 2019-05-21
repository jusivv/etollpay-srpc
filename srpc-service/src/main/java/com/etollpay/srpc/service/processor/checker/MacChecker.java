package com.etollpay.srpc.service.processor.checker;

import com.etollpay.srpc.service.processor.IPreProcessor;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Assert;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.ServiceException;
import com.etollpay.srpc.tool.SysConfig;
import com.etollpay.srpc.tool.standard.MetadataHelper;

import javax.servlet.http.HttpServletRequest;

public class MacChecker implements IPreProcessor {
    @Override
    public void process(Metadata metadata, HttpServletRequest request) throws ServiceException {
        String secretKey = SysConfig.getString("secretKey." + metadata.getSender().toLowerCase());
        Assert.is(Common.isBlank(secretKey) ||
                        !MetadataHelper.verifyMac(metadata, metadata.getMac(), Common.decodeBase64(secretKey)),
                        IntfError.ILLEGAL_MAC);
    }

    @Override
    public boolean accept(String tag) {
        return Common.inArray(tag,
                new String[]{Metadata.REQUEST_TYPE_REQ, Metadata.REQUEST_TYPE_RES, Metadata.REQUEST_TYPE_ERR});
    }
}
