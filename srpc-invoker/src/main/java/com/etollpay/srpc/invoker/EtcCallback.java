package com.etollpay.srpc.invoker;

import okhttp3.Call;

import java.io.IOException;

/**
 * 异步执行的回调接口
 */
public interface EtcCallback {
    /**
     * 正常返回结果时调用的方法
     * @param responseMessage   响应信息
     */
    void response(ResponseMessage responseMessage);

    /**
     * 请求失败时调用的方法
     * @param call  原调用对象
     * @param e     异常
     */
    void requestError(Call call, IOException e);

    /**
     * 发生其他业务类异常时调用的方法
     * @param t     异常
     */
    void onError(Throwable t);
}

