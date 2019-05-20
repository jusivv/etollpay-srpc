package com.etollpay.srpc.tool.spi.intf;

import java.io.InputStream;
import java.io.OutputStream;

public interface IEncryptor extends IProviderSelector {

    void reload();

    OutputStream getEncryptStream(OutputStream dist, String recipent, String sender);

    InputStream getDecryptStream(InputStream src, String recipient, String sender);

    String getEncryption();
}
