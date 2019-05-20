package com.etollpay.srpc.message.basic;

public abstract class AbstractEtcMessagePublisher implements IEtcMessagePublisher {

    public void publish(String event, String[] objectIds) {
        if (event != null && objectIds != null && objectIds.length > 0) {
            send(event, objectIds);
        }
    }

    abstract protected void send(String event, String[] objectIds);
}
