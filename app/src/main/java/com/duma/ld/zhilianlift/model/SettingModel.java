package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2017/12/18.
 */

public class SettingModel {
    private String title;
    private String message;
    private boolean isImg;

    public SettingModel(String title) {
        this(title, "", true);
    }

    public SettingModel(String title, String message) {
        this(title, message, true);
    }

    public SettingModel(String title, String message, boolean isImg) {
        this.title = title;
        this.message = message;
        this.isImg = isImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isImg() {
        return isImg;
    }

    public void setImg(boolean img) {
        isImg = img;
    }
}
