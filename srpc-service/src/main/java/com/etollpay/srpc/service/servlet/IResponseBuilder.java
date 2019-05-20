package com.etollpay.srpc.service.servlet;

import com.etollpay.srpc.standard.basic.Metadata;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 同步响应构造器
 */
public interface IResponseBuilder {
    /**
     * 成功响应
     * @param resMetadata   响应元数据
     * @param response      HttpServletResponse
     * @throws IOException
     */
    void success(Metadata resMetadata, HttpServletResponse response) throws IOException;

    /**
     * 成功响应
     * @param resMetadata   响应元数据
     * @param response      HttpServletResponse
     * @param statusCode    状态码
     * @throws IOException
     */
    void success(Metadata resMetadata, HttpServletResponse response, int statusCode) throws IOException;

    /**
     * 异常响应
     * @param t         异常
     * @param metadata  元数据
     * @param response  HttpServletResponse
     */
    void error(Throwable t, Metadata metadata, HttpServletResponse response);

    /**
     * 异常响应
     * @param t             异常
     * @param metadata      元数据
     * @param response      HttpServletResponse
     * @param statusCode    状态码
     */
    void error(Throwable t, Metadata metadata, HttpServletResponse response, int statusCode);
}
