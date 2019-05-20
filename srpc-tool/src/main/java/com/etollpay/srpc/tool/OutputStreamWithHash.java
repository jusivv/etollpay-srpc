package com.etollpay.srpc.tool;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;

public class OutputStreamWithHash extends OutputStream {
    private OutputStream outputStream;
    private MessageDigest digest;
    public OutputStreamWithHash(OutputStream outputStream, String digestAlgorithm) throws NoSuchAlgorithmException {
        super();
        this.digest = MessageDigest.getInstance(digestAlgorithm);
        this.outputStream = outputStream;

    }

    public OutputStreamWithHash(OutputStream outputStream, String digestAlgorithm, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException {
        super();
        this.digest = MessageDigest.getInstance(digestAlgorithm, provider);
        this.outputStream = outputStream;
    }

    public OutputStreamWithHash(OutputStream outputStream, String digestAlgorithm, Provider provider)
            throws NoSuchAlgorithmException {
        super();
        this.digest = MessageDigest.getInstance(digestAlgorithm, provider);
        this.outputStream = outputStream;
    }

    @Override
    public void write(byte[] b) throws IOException {
        outputStream.write(b);
        digest.update(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
        digest.update(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
        digest.update((byte) b);
    }

    public byte[] getHashBytes() {
        return digest.digest();
    }

    public String getHashCode() {
        return Common.byteToHex(getHashBytes());
    }
}
