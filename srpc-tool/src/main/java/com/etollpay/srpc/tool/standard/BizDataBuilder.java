package com.etollpay.srpc.tool.standard;

import com.alibaba.fastjson.JSON;
import com.etollpay.srpc.standard.basic.ICsvBean;
import com.etollpay.srpc.tool.Common;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class BizDataBuilder {
    private static Logger log = LoggerFactory.getLogger(BizDataBuilder.class);


    /**
     * 所有文件名的集合
     */
    private Set<String> allFileNames;
    /**
     * JSON文件内容集合
     */
    private Map<String, Object> jsonFiles;
    /**
     * CSV文件内容集合
     */
    private Map<String, List<ICsvBean>> csvFiles;
    /**
     * 其他文件集合
     */
    private Map<String, Path> otherFiles;

    public BizDataBuilder() {
        allFileNames = new HashSet<String>();
        jsonFiles = new HashMap<String, Object>();
        csvFiles = new HashMap<String, List<ICsvBean>>();
        otherFiles = new HashMap<String, Path>();
    }


    public void addJson(String fileName, Object bean) {
        if (allFileNames.add(fileName)) {
            jsonFiles.put(fileName, bean);
        } else {
            throw new RuntimeException("duplicate file name " + fileName);
        }
    }

    public void addCsv(String fileName, ICsvBean bean) {
        allFileNames.add(fileName);

        List<ICsvBean> list = csvFiles.get(fileName);
        if (list == null) {
            list = new ArrayList<ICsvBean>();
            csvFiles.put(fileName, list);
        }
        list.add(bean);
    }

    public void addFile(Path path) {
        String fileName = path.getName(path.getNameCount() - 1).toString();
        if (allFileNames.add(fileName)) {
            otherFiles.put(fileName, path);
        } else {
            throw new RuntimeException("duplicate file name " + fileName);
        }
    }

    public String getOutputFileType() {
        if (allFileNames.isEmpty()) {
            return null;
        }
        if (allFileNames.size() == 1) {
            String fileName = allFileNames.iterator().next();
           return fileName.substring(fileName.lastIndexOf('.') + 1);
        } else {
            return "zip";
        }
    }

    public void build(OutputStream outputStream) throws IOException, NoSuchFieldException, IllegalAccessException {
        if (allFileNames.isEmpty()) {
            // no file
            return;
        }
        if (allFileNames.size() == 1) {
            // single file
            String fileName = allFileNames.iterator().next();
            log.debug("found file: {}", fileName);
            String extName = fileName.substring(fileName.lastIndexOf('.') + 1);
            if (extName.equalsIgnoreCase("JSON") && jsonFiles.containsKey(fileName)) {
                // JSON File
                Object jsonBean = jsonFiles.get(fileName);
                String jsonString = jsonBean != null ? JSON.toJSONString(jsonBean) : null;
                if (jsonString != null) {
                    outputStream.write(jsonString.getBytes(Charset.forName("UTF-8")));
                    outputStream.flush();
                }
            } else if (extName.equalsIgnoreCase("CSV") && csvFiles.containsKey(fileName)) {
                // CSV File
                List<ICsvBean> csvList = csvFiles.get(fileName);
                if (csvList != null && !csvList.isEmpty()) {
                    for (Iterator<ICsvBean> it = csvList.iterator(); it.hasNext(); ) {
                        ICsvBean csvBean = it.next();
                        if (csvBean != null) {
                            Writer writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
                            Common.outputCsv(csvBean, writer);
                        }
                    }
                }
            } else if (otherFiles.containsKey(fileName)) {
                // Other File
                Path path = otherFiles.get(fileName);
                if (Files.exists(path) && !Files.isDirectory(path)) {
                    InputStream fis = Common.createInputStream(path);
                    try {
                        Common.copyStream(fis, outputStream);
                        outputStream.flush();
                    } finally {
                        IOUtils.closeQuietly(fis);
                    }
                }
            }
        } else {
            // multi file
            ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(outputStream);
            try {
                if (!jsonFiles.isEmpty()) {
                    for (Iterator<String> it = jsonFiles.keySet().iterator(); it.hasNext(); ) {
                        String fileName = it.next();
                        log.debug("found file: {}", fileName);
                        Object jsonBean = jsonFiles.get(fileName);
                        String jsonString = jsonBean != null ? JSON.toJSONString(jsonBean) : null;
                        if (jsonString != null) {
                            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(fileName);
                            zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);
                            zipArchiveOutputStream.write(jsonString.getBytes(Charset.forName("UTF-8")));
                            zipArchiveOutputStream.closeArchiveEntry();
                        }
                    }
                }
                if (!csvFiles.isEmpty()) {
                    for (Iterator<String> it = csvFiles.keySet().iterator(); it.hasNext(); ) {
                        String fileName = it.next();
                        log.debug("found file: {}", fileName);
                        List<ICsvBean> set = csvFiles.get(fileName);
                        if (set != null && !set.isEmpty()) {
                            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(fileName);
                            zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);
                            for (Iterator<ICsvBean> iterator = set.iterator(); iterator.hasNext(); ) {
                                ICsvBean csvBean = iterator.next();
                                if (csvBean != null) {
                                    Writer writer = new OutputStreamWriter(zipArchiveOutputStream,
                                            Charset.forName("UTF-8"));
                                    Common.outputCsv(csvBean, writer);
                                }
                            }
                            zipArchiveOutputStream.closeArchiveEntry();
                        }

                    }
                }
                if (!otherFiles.isEmpty()) {
                    for (Iterator<String> it = otherFiles.keySet().iterator(); it.hasNext(); ) {
                        String fileName = it.next();
                        log.debug("found file: {}", fileName);
                        Path path = otherFiles.get(fileName);
                        if (path != null && Files.exists(path) && !Files.isDirectory(path)) {
                            File file = path.toFile();
                            log.debug("found file: {}", file.getName());
                            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
                            zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);
                            InputStream fis = Common.createInputStream(path);
                            try {
                                IOUtils.copy(fis, zipArchiveOutputStream);
                            } finally {
                                zipArchiveOutputStream.closeArchiveEntry();
                                IOUtils.closeQuietly(fis);
                            }
                        }
                    }
                }
            } finally {
                IOUtils.closeQuietly(zipArchiveOutputStream);
            }
        }
    }
}
