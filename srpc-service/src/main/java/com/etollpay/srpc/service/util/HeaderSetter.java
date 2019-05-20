package com.etollpay.srpc.service.util;


import com.etollpay.srpc.tool.ValueSetter;

import javax.servlet.http.HttpServletResponse;

public class HeaderSetter implements ValueSetter<String> {
    private HttpServletResponse response;

    public HeaderSetter(HttpServletResponse response) {
        this.response = response;
    }

    public void set(String key, String value) {
        response.addHeader(key, value);
    }
}
