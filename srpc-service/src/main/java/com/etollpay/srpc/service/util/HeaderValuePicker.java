package com.etollpay.srpc.service.util;


import com.etollpay.srpc.tool.ValuePicker;

import javax.servlet.http.HttpServletRequest;

public class HeaderValuePicker implements ValuePicker<String> {
    private HttpServletRequest request;

    public HeaderValuePicker(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String pick(String key) {
        return request.getHeader(key);
    }
}
