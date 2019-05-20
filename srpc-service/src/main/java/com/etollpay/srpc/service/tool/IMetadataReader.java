package com.etollpay.srpc.service.tool;


import com.etollpay.srpc.standard.basic.Metadata;

import javax.servlet.http.HttpServletRequest;

/**
 * 元数据读取接口
 */
public interface IMetadataReader {
    /**
     * 从Request中读取元数据
     * @param request   请求
     * @return          元数据
     */
    Metadata read(HttpServletRequest request);
}
