package com.etollpay.srpc.tool.spi;

import com.etollpay.srpc.tool.spi.intf.IProviderSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ServiceHelper {
    private static Logger log = LoggerFactory.getLogger(ServiceHelper.class);

    public static <T extends IProviderSelector> T getProvider(String tag, Class<T> clazz) {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        for (Iterator<T> it = serviceLoader.iterator(); it.hasNext(); ) {
            T provider = it.next();
            if (provider.accept(tag)) {
                log.debug("hit provider: {} for class: {}", provider.getClass().getName(), clazz.getName());
                return provider;
            }
        }
        log.warn("no provider accept tag: {} for class: {}", tag, clazz.getName());
        return null;
    }


    public static <T> Iterator<T> getProviders(Class<T> clazz) {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        return serviceLoader.iterator();
    }

}
