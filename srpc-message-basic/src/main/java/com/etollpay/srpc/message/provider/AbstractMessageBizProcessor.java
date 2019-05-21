package com.etollpay.srpc.message.provider;

import com.etollpay.srpc.message.basic.IEtcMessagePublisher;
import com.etollpay.srpc.tool.SysConfig;
import com.etollpay.srpc.tool.component.SpringContextHolder;
import com.etollpay.srpc.tool.spi.provider.AbstractBizProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class AbstractMessageBizProcessor extends AbstractBizProcessor {
    private static Logger log = LoggerFactory.getLogger(AbstractMessageBizProcessor.class);

    private Map<String, List<String>> messages;

    public AbstractMessageBizProcessor() {
        messages = new HashMap<String, List<String>>();
    }

    protected void publish(String  event, String... ids) {
        List<String> idList = messages.get(event);
        if (idList == null) {
            idList = new ArrayList<String>();
            messages.put(event, idList);
        }
        for (String id : ids) {
            idList.add(id);
        }
    }

    public void doPublish() {
        if (SysConfig.getBool("srpc.send.event.message", false)) {
            try {
                if (!messages.isEmpty()) {
                    IEtcMessagePublisher messagePublisher = SpringContextHolder.getBean(IEtcMessagePublisher.class);
                    if (messagePublisher != null) {
                        for (Iterator<String> it = messages.keySet().iterator(); it.hasNext(); ) {
                            String event = it.next();
                            List<String> idList = messages.get(event);
                            if (!idList.isEmpty()) {
                                messagePublisher.publish(event, idList.toArray(new String[0]));
                                log.debug("published {} id(s) of {}", idList.size(), event);
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                log.error(t.getLocalizedMessage(), t);
            }
        }
    }
}
