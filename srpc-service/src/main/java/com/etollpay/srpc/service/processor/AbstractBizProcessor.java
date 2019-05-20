package com.etollpay.srpc.service.processor;

import com.etollpay.srpc.message.basic.IEtcMessagePublisher;
import com.etollpay.srpc.service.component.SpringContextHolder;
import com.etollpay.srpc.service.util.ServiceException;
import com.etollpay.srpc.service.util.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.nio.file.Path;
import java.util.*;

public abstract class AbstractBizProcessor implements IBizProcessor {
    private static Logger log = LoggerFactory.getLogger(AbstractBizProcessor.class);

    private Map<String, List<String>> messages;

    public AbstractBizProcessor() {
        messages = new HashMap<String, List<String>>();
    }

    @Override
    public String processRequest(Path workspace, Path bizFilePath,
                               String sender, OutputStream resOutput) throws Exception {
        throw new ServiceException("no request processor", 500);
    }

    @Override
    public String processResponse(Path workspace, Path bizFilePath,
                                  String sender, OutputStream resOutput) throws Exception {
        throw new ServiceException("no response processor", 500);
    }

    @Override
    public String processError(Path workspace, Path bizFilePath,
                             String sender, OutputStream resOutput) throws Exception {
        throw new ServiceException("no error processor", 500);
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
