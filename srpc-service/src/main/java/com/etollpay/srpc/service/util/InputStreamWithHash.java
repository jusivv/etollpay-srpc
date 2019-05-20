package com.etollpay.srpc.service.util;


import com.etollpay.srpc.tool.Common;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class InputStreamWithHash extends InputStream {

    private InputStream inputStream;

    private MessageDigest digest;

    public InputStreamWithHash(InputStream inputStream, String digestAlgorithm) throws NoSuchAlgorithmException {
        this.inputStream = inputStream;
        this.digest = MessageDigest.getInstance(digestAlgorithm);
    }

    public InputStreamWithHash(InputStream inputStream, String digestAlgorithm, Provider provider)
            throws NoSuchAlgorithmException {
        this.inputStream = inputStream;
        this.digest = MessageDigest.getInstance(digestAlgorithm, provider);
    }

    @Override
    public int read() throws IOException {
        int i = inputStream.read();
        if (i == -1) {
            return -1;
        }
        digest.update((byte) i);
        return i;
    }

    @Override
    public long skip(long n) throws IOException {
        return inputStream.skip(n);
    }

    @Override
    public int available() throws IOException {
        return inputStream.available();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        inputStream.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        inputStream.reset();
    }

    @Override
    public boolean markSupported() {
        return inputStream.markSupported();
    }

    public byte[] getHashBytes() {
        return digest.digest();
    }

    public String getHashCode() {
        return Common.byteToHex(getHashBytes());
    }
}
