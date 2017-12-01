package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2017/5/26.
 */

public class HttpResModel<T> implements Serializable {
    public String status;
    public String msg;
    public T result;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
