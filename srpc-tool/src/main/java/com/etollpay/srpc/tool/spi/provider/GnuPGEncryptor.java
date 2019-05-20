package com.etollpay.srpc.tool.spi.provider;


import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.spi.intf.IEncryptor;
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.BouncyGPG;
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.keys.callbacks.KeyringConfigCallbacks;
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.keys.keyrings.KeyringConfig;
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.keys.keyrings.KeyringConfigs;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.Security;
import java.util.Properties;

public class GnuPGEncryptor implements IEncryptor {
    private static Logger log = LoggerFactory.getLogger(GnuPGEncryptor.class);

    private KeyringConfig keyringConfig = null;

    private Properties properties = new Properties();

    public GnuPGEncryptor() {
        // 初始化环境
        if (Security.getProperty(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        reload();
    }

    public void reload() {
        // 加载配置
        properties.clear();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("gnupg.properties"));
            String pubring = properties.getProperty("pubring", "");
            String secring = properties.getProperty("secring", "");
            String secPwd = properties.getProperty("secring.password", "");
            secPwd = new String(Common.decodeBase64(secPwd), Charset.forName("UTF-8"));
            keyringConfig = KeyringConfigs.withKeyRingsFromStreams(
                    this.getClass().getClassLoader().getResourceAsStream(pubring),
                    this.getClass().getClassLoader().getResourceAsStream(secring),
                    KeyringConfigCallbacks.withPassword(secPwd));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        } catch (PGPException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public OutputStream getEncryptStream(OutputStream dist, String recipent, String sender) {
        // 获取加密流
        try {
            return BouncyGPG.encryptToStream().withConfig(keyringConfig).withStrongAlgorithms()
                    .toRecipient(properties.getProperty("uid." + recipent.toLowerCase(), ""))
                    .andSignWith(properties.getProperty("uid." + sender.toLowerCase(), ""))
                    .armorAsciiOutput().andWriteTo(dist);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public InputStream getDecryptStream(InputStream src, String recipient, String sender) {
        // 获取解密流
        try {
            return BouncyGPG.decryptAndVerifyStream().withConfig(keyringConfig)
                    .andRequireSignatureFromAllKeys(properties.getProperty("uid." + sender, ""))
                    .fromEncryptedInputStream(src);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public boolean accept(String tag) {
        return "PGP".equalsIgnoreCase(tag) || "GPG".equalsIgnoreCase(tag) || "GnuPG".equalsIgnoreCase(tag);
    }

    public String getEncryption() {
        return Metadata.FILE_ENCRYPTION_PGP;
    }
}
