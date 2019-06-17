package com.etollpay.srpc.tool;

import com.etollpay.srpc.standard.basic.ICsvBean;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Common {
    private static Logger log = LoggerFactory.getLogger(Common.class);

    /**
     * 判断字符串是null或空
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().equals("");
    }

    /**
     * 将null转换为空字符串
     * @param str
     * @return
     */
    public static String null2Str(String str) {
        return str != null ? str.trim() : "";
    }

    /**
     * 判断字符是否在字符数组中
     * @param str
     * @param arry
     * @return
     */
    public static boolean inArray(String str, String[] arry) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < arry.length; i++) {
            String s = arry[i];
            if (str.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 计算哈希值
     * @param data          原始数据
     * @param secretKey     密钥
     * @param algorithm     哈希算法
     * @return
     */
    public static byte[] getMac(byte[] data, byte[] secretKey, String algorithm) {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey, algorithm);
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(keySpec);
            return mac.doFinal(data);
        } catch (GeneralSecurityException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
    }
    public static String md5(byte[] data){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data);
            byte[] result = digest.digest();
            return byteToHex(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
    /**
     * 字节数组转16进制字符串
     * @param data  字节数组
     * @return
     */
    public static String byteToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String s = Integer.toHexString(data[i] & 0xFF);
            if (s.length() == 1) {
                sb.append('0');
            }
            sb.append(s);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 解密Base64字符串
     * @param base64String  Base64字符串
     * @return
     */
    public static byte[] decodeBase64(String base64String) {
        return Base64.decode(base64String);
    }

    /**
     * 获取UUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 压缩（Zip）文件
     * @param directory     目录
     * @param target        目标压缩文件名
     * @param sources       源文件名
     * @throws IOException
     */
    public static void compress(String directory, String target, String... sources) throws IOException {
        OutputStream targetStream = Files.newOutputStream(Paths.get(directory, target));
        ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(new BufferedOutputStream(targetStream,
                8 *1024));
        try {
            for (int i = 0; i < sources.length; i++) {
                Path path = Paths.get(directory, sources[i]);
                if (Files.exists(path)) {
                    InputStream inputStream = createInputStream(path);
                    try {
                        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(path.toFile(),
                                path.getName(path.getNameCount() - 1).toString());
                        zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);
                        IOUtils.copy(inputStream, zipArchiveOutputStream);
                        zipArchiveOutputStream.closeArchiveEntry();
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                }
            }
        } finally {
            IOUtils.closeQuietly(zipArchiveOutputStream);
            IOUtils.closeQuietly(targetStream);
        }
    }

    /**
     * 解压缩（zip）文件
     * @param directory 目录
     * @param fileName  压缩文件名
     * @throws IOException
     */
    public static void uncompress(String directory, String fileName) throws IOException {
        uncompress(Paths.get(directory, fileName));
    }

    /**
     * 解压缩（zip）文件
     * @param path          压缩文件路径
     * @throws IOException
     */
    public static void uncompress(Path path) throws IOException {
        Path parent = path.getParent();
        InputStream inputStream = createInputStream(path);
        try {
            ZipArchiveInputStream zipArchiveInputStream = new ZipArchiveInputStream(inputStream);
            ZipArchiveEntry zipArchiveEntry = null;
            while ((zipArchiveEntry = zipArchiveInputStream.getNextZipEntry()) != null) {
                if (zipArchiveEntry.isDirectory()) {
                    Files.createDirectories(parent.resolve(zipArchiveEntry.getName()));
                } else {
                    OutputStream fileOut = Files.newOutputStream(path.getParent().resolve(zipArchiveEntry.getName()));
                    OutputStream outputStream = new BufferedOutputStream(fileOut,8 * 1024);
                    try {
                        IOUtils.copy(zipArchiveInputStream, outputStream);
                    } finally {
                        if (outputStream != null) {
                            outputStream.flush();
                            outputStream.close();
                        }
                    }
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    /**
     * 创建文件输入流
     * @param directory 目录
     * @param fileName  文件名
     * @return
     * @throws IOException
     */
    public static InputStream createInputStream(String directory, String fileName) throws IOException {
        return createInputStream(Paths.get(directory, fileName));
    }

    /**
     * 创建文件输入流
     * @param path
     * @return
     * @throws IOException
     */
    public static InputStream createInputStream(Path path) throws IOException {
        return new BufferedInputStream(Files.newInputStream(path), 8 * 1024);
    }

    /**
     * 将CSV文件转换为CsvBean集合（默认CSV文件中不包含标题行）
     * @param reader
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    public static <T extends ICsvBean> List<T> parseCsvBeans(Reader reader, Class<T> clazz)
            throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        return parseCsvBeans(reader, clazz, false, QuoteMode.NON_NUMERIC);
    }

    /**
     * 将CSV文件转换为CsvBean集合
     * @param reader        CSV文件
     * @param clazz         数据类
     * @param hasHeader     CSV文件中是否包含标题行
     * @param <T>
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    public static <T extends ICsvBean> List<T> parseCsvBeans(Reader reader, Class<T> clazz, boolean hasHeader,
                                                             QuoteMode quoteMode)
            throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        final List<T> result = new ArrayList<T>();
        parseCsvBeans(reader, clazz, new IBeanIterator<T>() {
            public boolean iterate(T bean) {
                result.add(bean);
                return true;
            }
        }, hasHeader, quoteMode);
        return result;
    }

    /**
     * 提取CSV文件中的数据（默认CSV文件中无标题行）
     * @param reader        CSV文件
     * @param clazz         数据类
     * @param beanIterator  对象迭代器
     * @param <T>
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    public static <T extends ICsvBean> void parseCsvBeans(Reader reader, Class<T> clazz,
                                                          IBeanIterator<T> beanIterator)
            throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        parseCsvBeans(reader, clazz, beanIterator, false, QuoteMode.NON_NUMERIC);
    }

    /**
     * 提取CSV文件中的数据
     * @param reader        CSV文件
     * @param clazz         数据类
     * @param beanIterator  对象迭代器
     * @param hasHeader    CSV文件中是否包含标题
     * @param <T>
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    public static <T extends ICsvBean> void parseCsvBeans(Reader reader, Class<T> clazz, IBeanIterator<T> beanIterator,
                                                          boolean hasHeader, QuoteMode quoteMode)
            throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        if (beanIterator == null) {
            return;
        }
        Iterable<CSVRecord> records = hasHeader ? CSVFormat.EXCEL.withFirstRecordAsHeader().withQuoteMode(quoteMode).parse(reader) :
                CSVFormat.EXCEL.withQuoteMode(quoteMode).parse(reader);
        for (Iterator<CSVRecord> it = records.iterator(); it.hasNext(); ) {
            // line
            CSVRecord record = it.next();
            T csvBean = clazz.newInstance();
            String[] fields = csvBean.getFields();
            // column
            for (int i = 0; i < fields.length; i++) {
                if (i < record.size()) {
                    String fieldName = fields[i];
                    Field field = clazz.getDeclaredField(fieldName);
                    String value = record.get(i).trim();
                    field.setAccessible(true);
                    try {
                        if (field.getType().isAssignableFrom(Integer.TYPE) ||
                                field.getType().isAssignableFrom(Integer.class)) {
                            field.set(csvBean, Integer.parseInt(value));
                        } else if (field.getType().isAssignableFrom(Long.TYPE) ||
                                field.getType().isAssignableFrom(Long.class)) {
                            field.set(csvBean, Long.parseLong(value));
                        } else if (field.getType().isAssignableFrom(Float.TYPE) ||
                                field.getType().isAssignableFrom(Float.class)) {
                            field.set(csvBean, Float.parseFloat(value));
                        } else if (field.getType().isAssignableFrom(Double.TYPE) ||
                                field.getType().isAssignableFrom(Double.class)) {
                            field.set(csvBean, Double.parseDouble(value));
                        } else if (field.getType().isAssignableFrom(Boolean.TYPE) ||
                                field.getType().isAssignableFrom(Boolean.class)) {
                            field.set(csvBean, Boolean.parseBoolean(value));
                        } else if (field.getType().isAssignableFrom(String.class)) {
                            field.set(csvBean, value);
                        } else {
                            throw new IllegalAccessException(
                                    "illegal field type when set " + field.getName() + " with value " + value + " in " +
                                            clazz.getName() + ". expect type " + field.getType().getName());
                        }
                    } catch (NumberFormatException e) {
                        log.error("parse field {} error", fieldName, e);
                        throw e;
                    }
                } else {
                    break;
                }
            }
            if (!beanIterator.iterate(csvBean)) {
                break;
            }
        }
    }

    /**
     *
     * @param csvBean
     * @param out
     * @param withHeader
     * @param <T>
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public static <T extends ICsvBean> void outputCsv(T csvBean, Appendable out, boolean withHeader, QuoteMode quoteMode)
            throws NoSuchFieldException, IllegalAccessException, IOException {
        String[] fields = csvBean.getFields();
        CSVPrinter printer = withHeader ? CSVFormat.EXCEL.withQuoteMode(quoteMode).withHeader(fields).print(out)
                : CSVFormat.EXCEL.withQuoteMode(quoteMode).print(out);
        for (int i = 0; i < fields.length; i++) {
            Field field = csvBean.getClass().getDeclaredField(fields[i]);
            field.setAccessible(true);
            printer.print(field.get(csvBean));
        }
        printer.println();
        printer.flush();
    }

    /**
     * 将CsvBean输出为CSV行（默认不输出标题行）
     * @param csvBean
     * @param out
     * @param <T>
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public static <T extends ICsvBean> void outputCsv(T csvBean, Appendable out)
            throws NoSuchFieldException, IllegalAccessException, IOException {
        outputCsv(csvBean, out, false, QuoteMode.NON_NUMERIC);
    }

    /**
     * 将数据从输入流写入多个输出流
     * @param inputStream
     * @param outputStreams
     * @throws IOException
     */
    public static void copyStream(InputStream inputStream, OutputStream... outputStreams) throws IOException {
        byte[] buff = new byte[4 * 1024];
        int len;
        while ((len = inputStream.read(buff)) != -1) {
            for (int i = 0; i < outputStreams.length; i++) {
                outputStreams[i].write(buff, 0, len);
            }
        }
    }

    /**
     * 复制输入流到多个输出流，并计算输入数据的hash
     * @param inputStream
     * @param digestAlgorithm
     * @param outputStreams
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static String copyStreamWithHashCode(InputStream inputStream, String digestAlgorithm,
                                                OutputStream... outputStreams) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(digestAlgorithm);
        byte[] buff = new byte[4 * 1024];
        int len;
        while ((len = inputStream.read(buff)) != -1) {
            for (int i = 0; i < outputStreams.length; i++) {
                outputStreams[i].write(buff, 0, len);
            }
            digest.update(buff, 0, len);
        }
        return byteToHex(digest.digest());
    }

    /**
     * 依照系统时间生成批次号
     */
    public static String getBatchNo() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 20) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
        }
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        return sdf.format(calendar.getTime());
    }


    /**
     * 卡片注销生成批次号（涉及31天）
     */
    public static String getBatchNo(long unregister31Time) {

        //取得明日零点零分零秒的时间
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long tomorrowTime = calendar.getTimeInMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if(unregister31Time < tomorrowTime){
            return sdf.format(new Date(tomorrowTime))+"01";
        }else{
            return sdf.format(new Date(unregister31Time))+"01";
        }

    }

    public static void setPermission(Path path, PosixFilePermission... permissions) throws IOException {
        if (Files.exists(path)) {
            Set<PosixFilePermission> set = new HashSet<>();
            for (PosixFilePermission permission : permissions) {
                set.add(permission);
            }
            try {

                Files.setPosixFilePermissions(path, set);
            } catch (UnsupportedOperationException e) {
                log.warn(e.getLocalizedMessage(), e);
            }
        }
    }


}
