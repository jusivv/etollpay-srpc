package com.etollpay.srpc.service.util;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MultiOutputStream extends OutputStream {

    private OutputStream[] outputStreams;

    private List<MessageDigest> digests = new ArrayList<MessageDigest>();

    public MultiOutputStream(OutputStream ... outputStreams) {
        super();
        this.outputStreams = outputStreams;
    }

    public void addDigest(MessageDigest digest) {
        digests.add(digest);
    }

    public void clearDigest() {
        digests.clear();
    }

    @Override
    public void write(int b) throws IOException {
        if (outputStreams != null) {
            for (int i = 0; i < outputStreams.length; i++) {
                outputStreams[i].write(b);
            }
            for (int i = 0; i < digests.size(); i++) {
                digests.get(i).update((byte)(b & 0xFF));
            }
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        if (outputStreams != null) {
            for (int i = 0; i < outputStreams.length; i++) {
                outputStreams[i].write(b);
            }
            for (int i = 0; i < digests.size(); i++) {
                digests.get(i).update(b);
            }
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (outputStreams != null) {
            for (int i = 0; i < outputStreams.length; i++) {
                outputStreams[i].write(b, off, len);
            }
            for (int i = 0; i < digests.size(); i++) {
                digests.get(i).update(b, off, len);
            }
        }
    }

    @Override
    public void flush() throws IOException {
        if (outputStreams != null) {
            for (int i = 0; i < outputStreams.length; i++) {
                outputStreams[i].flush();
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (outputStreams != null) {
            for (int i = 0; i < outputStreams.length; i++) {
                outputStreams[i].close();
            }
        }
    }
}
