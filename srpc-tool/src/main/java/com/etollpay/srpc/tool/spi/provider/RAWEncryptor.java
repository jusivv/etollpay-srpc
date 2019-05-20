package com.etollpay.srpc.tool.spi.provider;

import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.spi.intf.IEncryptor;

import java.io.InputStream;
import java.io.OutputStream;

public class RAWEncryptor implements IEncryptor {

    public void reload() {

    }

    public OutputStream getEncryptStream(OutputStream dist, String recipent, String sender) {
        return dist;
    }

    public InputStream getDecryptStream(InputStream src, String recipient, String sender) {
        return src;
    }

    public boolean accept(String tag) {
        return Metadata.FILE_ENCRYPTION_RAW.equalsIgnoreCase(tag);
    }

    public String getEncryption() {
        return Metadata.FILE_ENCRYPTION_RAW;
    }
}
