package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2017/6/5.
 */

public class HttpSimpleResModel implements Serializable {
    private String status;
    private String msg;

    public HttpResModel toLzyResponse() {
        HttpResModel httpResResponse = new HttpResModel();
        httpResResponse.status = status;
        httpResResponse.msg = msg;
        httpResResponse.result = "";
        return httpResResponse;
    }
}
