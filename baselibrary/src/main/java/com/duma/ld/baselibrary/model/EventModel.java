package com.duma.ld.baselibrary.model;

public class EventModel<T> {
    //传递的code
    private int code;
    private T data;
    private String message;

    public EventModel(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public EventModel(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public EventModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public EventModel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}