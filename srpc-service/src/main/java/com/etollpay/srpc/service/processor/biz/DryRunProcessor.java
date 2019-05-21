package com.etollpay.srpc.service.processor.biz;

import com.alibaba.fastjson.JSON;
import com.etollpay.srpc.message.provider.AbstractMessageBizProcessor;
import com.etollpay.srpc.service.util.BizException;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.IntfList;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class DryRunProcessor extends AbstractMessageBizProcessor {
    private static Logger log = LoggerFactory.getLogger(DryRunProcessor.class);

    private static final int RUN_MODEL_CACHE = 1;

    private static final int RUN_MODEL_FILE = 2;

    private int runModel = RUN_MODEL_CACHE;

    private Metadata metadata;

    private class DryRunFileInfo {
        private String fileName, extName;
        private long fileSize;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getExtName() {
            return extName;
        }

        public void setExtName(String extName) {
            this.extName = extName;
        }

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }
    }

    @Override
    public String processRequest(Path workspace, Path bizFilePath, String sender, OutputStream resOutput)
            throws BizException {
        log.debug("Metadata: {}", metadata == null ? "null" : JSON.toJSONString(metadata));
        if (bizFilePath != null) {
            try {
                DryRunFileInfo fileInfo = new DryRunFileInfo();
                if (Files.exists(bizFilePath)) {
                    String fileName = bizFilePath.getName(bizFilePath.getNameCount() - 1).toString();
                    fileInfo.setFileName(fileName);
                    fileInfo.setExtName(fileName.substring(fileName.lastIndexOf('.') + 1));
                    fileInfo.setFileSize(Files.size(bizFilePath));
                } else {
                    throw new ServiceException(IntfError.FILE_NOT_FOUND);
                }
                switch (runModel) {
                    case RUN_MODEL_CACHE: {
                        // 使用输出流返回业务数据
                        JSON.writeJSONString(resOutput, Charset.forName("UTF-8"), fileInfo);
                        return "JSON";
                    }
                    case RUN_MODEL_FILE: {
                        // 使用文件路径返回业务数据
                        String fileName = bizFilePath.toFile().getName();
                        Path resPath = workspace.resolve("fileInfo_" + fileName);
                        BufferedWriter writer = Files.newBufferedWriter(resPath, Charset.forName("UTF-8"));
                        writer.write(JSON.toJSONString(fileInfo));
                        return resPath.toString();
                    }
                    default: {
                        throw new ServiceException(IntfError.INTERNAL_ERROR);
                    }
                }
            } catch (IOException e) {
                log.error(e.getLocalizedMessage(), e);
                throw new ServiceException(IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
            }
        } else {
            throw new ServiceException(IntfError.FILE_NOT_FOUND);
        }
    }

    @Override
    public boolean accept(String tag) {
        return IntfList.dryRun.toString().equalsIgnoreCase(tag);
    }
}
