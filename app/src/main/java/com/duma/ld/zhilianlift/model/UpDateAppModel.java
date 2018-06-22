package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/6/22.
 */

public class UpDateAppModel {

    /**
     * status : 200
     * msg : 线上
     * result : {"id":1,"app_path":"public/upload/appfile/android_1.0_180622_135728.apk","app_version":"1.0","app_log":"1.0","app_name":"更新","app_title":"更新","app_do":"0"}
     */

    private int status;
    private String msg;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * app_path : public/upload/appfile/android_1.0_180622_135728.apk
         * app_version : 1.0
         * app_log : 1.0
         * app_name : 更新
         * app_title : 更新
         * app_do : 0
         */

        private int id;
        private String app_path;
        private String app_version;
        private String app_log;
        private String app_name;
        private String app_title;
        private String app_do;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getApp_path() {
            return app_path;
        }

        public void setApp_path(String app_path) {
            this.app_path = app_path;
        }

        public String getApp_version() {
            return app_version;
        }

        public void setApp_version(String app_version) {
            this.app_version = app_version;
        }

        public String getApp_log() {
            return app_log;
        }

        public void setApp_log(String app_log) {
            this.app_log = app_log;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getApp_title() {
            return app_title;
        }

        public void setApp_title(String app_title) {
            this.app_title = app_title;
        }

        public String getApp_do() {
            return app_do;
        }

        public void setApp_do(String app_do) {
            this.app_do = app_do;
        }
    }
}
