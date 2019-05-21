package com.etollpay.srpc.service.util;

import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.ServiceException;
import com.etollpay.srpc.tool.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Calendar;
import java.util.Locale;

public class FilePath {
    private static Logger log = LoggerFactory.getLogger(FilePath.class);

    private static String formatInt(int i) {
        return i < 10 ? "0" + i : Integer.toString(i);
    }

    /**
     * 获取入站文件归档路径
     * @param metadata
     * @param created   是否创建目录
     * @return
     */
    public static String getInboundPath(Metadata metadata, boolean created) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(metadata.getTimestamp());
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = formatInt(month) + formatInt(day);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String time = formatInt(hour) + formatInt(minute);

        StringBuilder sb = new StringBuilder();
        sb.append(getBasePath("archive.directory")).append(metadata.getReqType())
                .append(File.separator).append(year).append(File.separator)
                .append(date).append(File.separator).append(metadata.getSender()).append(File.separator)
                .append(metadata.getApiName()).append(File.separator).append(time).append(File.separator);
        String dir = sb.toString();
        if (created) {
            createDirectories(dir);
        }
        return dir;
    }

    /**
     * 获取入站文件归档路径，不创建目录
     * @param metadata
     * @return
     */
    public static String getInboundPath(Metadata metadata) {
        return getInboundPath(metadata, false);
    }

    /**
     * 获取出站文件归档路径
     * @param metadata
     * @param created   是否创建目录
     * @return
     */
    public static String getOutboundPath(Metadata metadata, boolean created) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(metadata.getTimestamp());
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = formatInt(month) + formatInt(day);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String time = formatInt(hour) + formatInt(minute);

        StringBuilder sb = new StringBuilder();
        sb.append(getBasePath("archive.directory")).append(metadata.getReqType())
                .append(File.separator).append(year).append(File.separator)
                .append(date).append(File.separator).append(metadata.getRecipient()).append(File.separator)
                .append(metadata.getApiName()).append(File.separator).append(time).append(File.separator);
        String dir = sb.toString();
        if (created) {
            createDirectories(dir);
        }
        return dir;
    }

    /**
     * 获取出站文件归档路径，不创建目录
     * @param metadata
     * @return
     */
    public static String getOutboundPath(Metadata metadata) {
        return getOutboundPath(metadata, false);
    }

    /**
     * 根据关键字获取路径，路径自动补全
     * @param key
     * @return
     */
    private static String getBasePath(String key) {
        String basePath = SysConfig.getString(key);
        if (!basePath.endsWith(File.separator)) {
            basePath += File.separator;
        }
        return basePath;
    }

    /**
     * 获取入站FTP路径
     * @param metadata
     * @param created 若目录不存在是否创建
     * @return
     */
    public static String getFtpInboundPath(Metadata metadata, boolean created) {
        String dir = getBasePath("ftp.path") + metadata.getSender().toLowerCase() + File.separator +
                "upload" + File.separator;
        if (created) {
            createDirectories(dir);
        }
        return dir;
    }

    /**
     * 获取入站FTP路径
     * @param metadata
     * @return
     */
    public static String getFtpInboundPath(Metadata metadata) {
        return getFtpInboundPath(metadata, false);
    }

    public static String getFtpOutboundPath(Metadata metadata, boolean created) {
        String dir = getBasePath("ftp.path") + metadata.getRecipient() + File.separator + "download"
                + File.separator;
        if (created) {
            createDirectories(dir);
        }
        return dir;
    }

    /**
     * 获取出站FTP路径
     * @param metadata
     * @return
     */
    public static String getFtpOutboundPath(Metadata metadata) {
        return getFtpInboundPath(metadata, false);
    }

    /**
     * 获取临时文件路径
     * @param created   是否创建目录
     * @return
     */
    public static String getTempPath(boolean created) {
        StringBuilder sb = new StringBuilder();
        sb.append(SysConfig.getString("archive.directory")).append("temp")
                .append(File.separator).append(Common.getUUID()).append(File.separator);
        String dir = sb.toString();
        if (created) {
            createDirectories(dir);
        }
        return dir;
    }

    /**
     * 获取临时文件路径，不创建目录
     * @return
     */
    public static String getTempPath() {
        return getTempPath(false);
    }

    private static void createDirectories(String dir) {
        Path path = Paths.get(dir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
            }
        }
    }

    /**
     * 清空目录
     * @param target        要清空的目录
     * @throws IOException
     */
    public static void deleteDirectory(Path target) throws IOException {
        // Spring
//        FileSystemUtils.deleteRecursively(workspace.toFile());
        // Java NIO2
        try {
            Files.deleteIfExists(target);
        } catch (DirectoryNotEmptyException e) {
            Files.walkFileTree(target, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try {
                        Files.delete(file);
                    } catch (IOException ioe) {
                        log.error(ioe.getLocalizedMessage(), ioe);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    try {
                        Files.delete(dir);
                    } catch (IOException ioe) {
                        log.error(ioe.getLocalizedMessage(), ioe);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }
}
