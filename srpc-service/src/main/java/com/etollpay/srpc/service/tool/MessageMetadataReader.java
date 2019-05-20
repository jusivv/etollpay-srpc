package com.etollpay.srpc.service.tool;

import com.etollpay.srpc.service.util.HeaderValuePicker;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.standard.MetadataHelper;

import javax.servlet.http.HttpServletRequest;

public class MessageMetadataReader implements IMetadataReader {

    @Override
    public Metadata read(HttpServletRequest request) {
        return MetadataHelper.fromHeader(new HeaderValuePicker(request));
    }
}
