package com.etollpay.srpc.tool.standard;

import com.etollpay.srpc.standard.basic.HeaderKey;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.ValuePicker;
import com.etollpay.srpc.tool.ValueSetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MetadataHelper {
    private static Logger log = LoggerFactory.getLogger(MetadataHelper.class);

    /**
     * 用较少的字段创建元数据
     * @param apiName
     * @param sender
     * @param recipient
     * @param originReqId
     * @param errorCode
     * @return
     */
    public static Metadata createMetadata(String apiName, String sender, String recipient, String originReqId, int errorCode) {
        if (Common.isBlank(originReqId)) {
            originReqId = null;
            errorCode = 0;
        }
        Metadata metadata = new Metadata();
        metadata.setRequestId(Common.getUUID());
        metadata.setReqType(
                originReqId == null ? Metadata.REQUEST_TYPE_REQ :
                        errorCode == 0 ? Metadata.REQUEST_TYPE_RES : Metadata.REQUEST_TYPE_ERR);
        metadata.setApiName(apiName);
        metadata.setSender(sender);
        metadata.setRecipient(recipient);
        metadata.setTimestamp(System.currentTimeMillis());
        metadata.setOriginRequestId(originReqId);
        metadata.setErrorCode(errorCode);
        return metadata;
    }

    /**
     * 用较少的字段创建元数据
     * @param apiName
     * @param sender
     * @param recipient
     * @return
     */
    public static Metadata createMetadata(String apiName, String sender, String recipient) {
        return createMetadata(apiName, sender, recipient, null, 0);
    }

    /**
     * 用Header中的数据创建元数据对象
     * @param valuePicker
     * @return
     */
    public static Metadata fromHeader(ValuePicker<String> valuePicker) {
        if (valuePicker != null) {
            Metadata metadata = new Metadata();
            metadata.setRequestId(valuePicker.pick(HeaderKey.REQUEST_ID));
            metadata.setReqType(valuePicker.pick(HeaderKey.REQUEST_TYPE));
            metadata.setApiName(valuePicker.pick(HeaderKey.API_NAME));
            metadata.setSender(valuePicker.pick(HeaderKey.SENDER));
            metadata.setRecipient(valuePicker.pick(HeaderKey.RECIPIENT));
            String timestamp = valuePicker.pick(HeaderKey.TIMESTAMP);
            if (!Common.isBlank(timestamp)) {
                try {
                    metadata.setTimestamp(Long.parseLong(timestamp));
                } catch (NumberFormatException e) {
                    log.warn(e.getLocalizedMessage(), e);
                    metadata.setTimestamp(System.currentTimeMillis());
                }
            }
            String hasFile = valuePicker.pick(HeaderKey.HAS_FILE);
            metadata.setHasFile(!Common.isBlank(hasFile) && Common.inArray(hasFile, new String[]{"T", "Y", "true", "yes"}));
            metadata.setFileFormat(valuePicker.pick(HeaderKey.FILE_FORMAT));
            metadata.setFileEncryption(valuePicker.pick(HeaderKey.FILE_ENCRYPTION));
            metadata.setFileHashCode(valuePicker.pick(HeaderKey.FILE_HASH_CODE));
            metadata.setOriginRequestId(valuePicker.pick(HeaderKey.ORIGIN_REQUEST_ID));
            String errCode = valuePicker.pick(HeaderKey.ERROR_CODE);
            if (!Common.isBlank(errCode)) {
                try {
                    metadata.setErrorCode(Integer.parseInt(errCode));
                } catch (NumberFormatException e) {
                    log.error(e.getLocalizedMessage(), e);
                }
            }
            metadata.setMac(valuePicker.pick(HeaderKey.AUTHENTICATION_CODE));
            return metadata;
        } else {
            return null;
        }
    }

    /**
     * 将Metadata转换为Header Key，Value的Map
     * @param metadata
     * @return
     */
    public static Map<String, String> getHeader(Metadata metadata) {
        if (metadata != null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(HeaderKey.REQUEST_ID, metadata.getRequestId());
            map.put(HeaderKey.REQUEST_TYPE, metadata.getReqType());
            map.put(HeaderKey.API_NAME, metadata.getApiName());
            map.put(HeaderKey.SENDER, metadata.getSender());
            map.put(HeaderKey.RECIPIENT, metadata.getRecipient());
            map.put(HeaderKey.TIMESTAMP, metadata.getTimestamp() + "");
            map.put(HeaderKey.HAS_FILE, metadata.isHasFile() ? "true" : "false");
            if (metadata.getFileFormat() != null) {
                map.put(HeaderKey.FILE_FORMAT, metadata.getFileFormat());
            }
            if (metadata.getFileEncryption() != null) {
                map.put(HeaderKey.FILE_ENCRYPTION, metadata.getFileEncryption());
            }
            if (metadata.getFileHashCode() != null) {
                map.put(HeaderKey.FILE_HASH_CODE, metadata.getFileHashCode());
            }
            if (metadata.getOriginRequestId() != null) {
                map.put(HeaderKey.ORIGIN_REQUEST_ID, metadata.getOriginRequestId());
            }
            if (metadata.getReqType().equalsIgnoreCase(Metadata.REQUEST_TYPE_REQ)) {
                map.put(HeaderKey.ERROR_CODE, "0");
            } else {
                map.put(HeaderKey.ERROR_CODE, metadata.getErrorCode() + "");
            }
            map.put(HeaderKey.AUTHENTICATION_CODE, metadata.getMac());
            return map;
        } else {
            return null;
        }
    }

    /**
     * 使用元数据设置HTTP Header
     * @param metadata
     * @param valueSetter
     */
    public static void setHeaders(Metadata metadata, ValueSetter<String> valueSetter) {
        Map<String, String> headers = getHeader(metadata);
        for (Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = it.next();
            valueSetter.set(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 获取Metadata校验码
     * @param metadata  Metadata
     * @param secretKey 密钥
     * @return
     */
    public static String getMac(Metadata metadata, byte[] secretKey) {
        return Common.byteToHex(Common.getMac(
                toEncodeString(metadata).getBytes(Charset.forName("UTF-8")),
                secretKey, "HmacSHA256"));
    }

    /**
     * 验证Metadata校验码
     * @param metadata
     * @param mac
     * @param secretKey
     * @return
     */
    public static boolean verifyMac(Metadata metadata, String mac, byte[] secretKey) {
        return getMac(metadata, secretKey).equalsIgnoreCase(mac);
    }

    /**
     * 获取业务数据文件名
     * @param metadata
     * @return
     */
    public static String getBizFileName(Metadata metadata) {
        StringBuilder sb = new StringBuilder();
        sb.append(metadata.getReqType()).append("_").append(metadata.getSender()).append("_")
                .append(metadata.getRecipient()).append("_").append(metadata.getApiName())
                .append("_").append(metadata.getTimestamp()).append("_")
                .append(metadata.getRequestId()).append(".").append(metadata.getFileFormat())
                .append(".").append(metadata.getFileEncryption());
        return sb.toString();
    }

    /**
     * 获取加密前的原始业务数据文件名
     * @param metadata
     * @return
     */
    public static String getOriginBizFileName(Metadata metadata) {
        StringBuilder sb = new StringBuilder();
        sb.append(metadata.getReqType()).append("_").append(metadata.getSender()).append("_")
                .append(metadata.getRecipient()).append("_").append(metadata.getApiName())
                .append("_").append(metadata.getTimestamp()).append("_")
                .append(metadata.getRequestId()).append(".").append(metadata.getFileFormat());
        return sb.toString();
    }

    /**
     * 获取默认加密方法
     * @return
     */
    public static String getDefaultEncryption() {
        return Metadata.FILE_ENCRYPTION_PGP;
    }

    public static String getDefaultHashAlgorithm() {
        return "MD5";
    }

    private static String toEncodeString(Metadata metadata) {
        StringBuilder sb = new StringBuilder();
        sb.append(Common.null2Str(metadata.getRequestId())).append(",")
                .append(Common.null2Str(metadata.getReqType())).append(",")
                .append(Common.null2Str(metadata.getApiName())).append(",")
                .append(Common.null2Str(metadata.getSender())).append(",")
                .append(Common.null2Str(metadata.getRecipient())).append(",")
                .append(metadata.getTimestamp()).append(",")
                .append(metadata.isHasFile() ? 1 : 0).append(",")
                .append(Common.null2Str(metadata.getFileFormat())).append(",")
                .append(Common.null2Str(metadata.getFileEncryption())).append(",")
                .append(Common.null2Str(metadata.getFileHashCode())).append(",")
                .append(Common.null2Str(metadata.getOriginRequestId())).append(",")
                .append(metadata.getErrorCode());
        String str = sb.toString();
        log.debug("hmac plaintext: {}", str);
        return str;
    }
}
