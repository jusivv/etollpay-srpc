package com.etollpay.srpc.message.basic;

public interface IEtcMessagePublisher {
    void publish(String event, String[] objectIds);
}
