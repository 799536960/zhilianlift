package com.duma.ld.zhilianlift.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudong on 2018/1/16.
 */

public class HouseHttpInfoModel {
    private List<FilterAttrBean> filter_attr;

    public List<FilterAttrBean> getFilter_attr() {
        return filter_attr;
    }

    public void setFilter_attr(List<FilterAttrBean> filter_attr) {
        this.filter_attr = filter_attr;
    }

    public static class FilterAttrBean {
        /**
         * name : 物业类型
         * item : [{"name":"物业类型","so_key":"purpose","so_name":"住宅","so_value":"1","id":4,"type":2},{"name":"物业类型","so_key":"purpose","so_name":"别墅","so_value":"2","id":5,"type":2},{"name":"物业类型","so_key":"purpose","so_name":"写字楼","so_value":"3","id":6,"type":2},{"name":"物业类型","so_key":"purpose","so_name":"商铺","so_value":"4","id":7,"type":2}]
         */

        private String name;
        private List<ItemBean> item;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean implements Serializable {
            @Override
            public String toString() {
                return "ItemBean{" +
                        "name='" + name + '\'' +
                        ", so_key='" + so_key + '\'' +
                        ", so_name='" + so_name + '\'' +
                        ", so_value='" + so_value + '\'' +
                        ", id=" + id +
                        ", type=" + type +
                        ", isCheck=" + isCheck +
                        '}';
            }

            /**
             * name : 物业类型
             * so_key : purpose
             * so_name : 住宅
             * so_value : 1
             * id : 4
             * type : 2
             */


            private String name;
            private String so_key;
            private String so_name;
            private String so_value;
            private int id;
            private int type;
            private boolean isCheck;


            public ItemBean() {
                isCheck = false;
            }

            public ItemBean(String so_name) {
                this.so_name = so_name;
                isCheck = false;
            }

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSo_key() {
                return so_key;
            }

            public void setSo_key(String so_key) {
                this.so_key = so_key;
            }

            public String getSo_name() {
                return so_name;
            }

            public void setSo_name(String so_name) {
                this.so_name = so_name;
            }

            public String getSo_value() {
                return so_value;
            }

            public void setSo_value(String so_value) {
                this.so_value = so_value;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
