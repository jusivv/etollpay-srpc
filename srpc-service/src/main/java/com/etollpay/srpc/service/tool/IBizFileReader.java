package com.etollpay.srpc.service.tool;


import com.etollpay.srpc.standard.basic.Metadata;

import javax.servlet.http.HttpServletRequest;

/**
 * 业务数据文件读取器
 */
public interface IBizFileReader {

    /**
     * 根据元数据读取业务数据文件到归档目录，并在合适的时机计算文件哈希值
     * @param metadata  元数据
     * @param request   Request
     * @return          非null时，为业务数据文件的哈希值，默认哈希算法为MD5
     */
    String read(Metadata metadata, HttpServletRequest request);

    /**
     * 根据元数据读取业务数据文件到归档目录，并在合适的时机计算文件哈希值
     * @param metadata          元数据
     * @param request           Request
     * @param hashAlgorithm     哈希算法
     * @return                  非null时，为业务数据文件的哈希值
     */
    String read(Metadata metadata, HttpServletRequest request, String hashAlgorithm);
}
