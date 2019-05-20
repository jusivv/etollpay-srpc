package com.etollpay.srpc.service.processor.checker;

import com.etollpay.srpc.service.processor.IPreProcessor;
import com.etollpay.srpc.service.util.Assert;
import com.etollpay.srpc.service.util.ServiceException;
import com.etollpay.srpc.service.util.SysConfig;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Common;

import javax.servlet.http.HttpServletRequest;

public class PrivilegeChecker implements IPreProcessor {
    @Override
    public void process(Metadata metadata, HttpServletRequest request) throws ServiceException {
        String[] privileges = SysConfig.getStringArray("privilege." + metadata.getSender().toLowerCase());
        Assert.is(
                privileges == null || !Common.inArray(metadata.getApiName(), privileges),
                IntfError.PRIVILEGE_AUTHENTICATION_FAILED, metadata.getSender(), metadata.getApiName());
    }

    @Override
    public boolean accept(String tag) {
        return Common.inArray(tag,
                new String[]{Metadata.REQUEST_TYPE_REQ, Metadata.REQUEST_TYPE_RES, Metadata.REQUEST_TYPE_ERR});
    }
}
