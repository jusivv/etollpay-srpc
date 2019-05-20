package com.etollpay.srpc.service.processor;


import com.etollpay.srpc.service.util.ServiceException;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.spi.intf.IProviderSelector;

import javax.servlet.http.HttpServletRequest;

public interface IPreProcessor extends IProviderSelector {

    void process(Metadata metadata, HttpServletRequest request) throws ServiceException;
}
