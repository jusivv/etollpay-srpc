package com.etollpay.srpc.message.basic;

import com.etollpay.srpc.tool.spi.intf.IProviderSelector;
import org.springframework.context.ApplicationContext;

public interface IEtcMessageSubscriber extends IProviderSelector {

    void subscribe(String[] objectIds, ApplicationContext context);
}
