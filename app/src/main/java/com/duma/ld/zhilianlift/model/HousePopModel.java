package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/19.
 */

public class HousePopModel {

    private List<FilterAttrBean> filter_attr;

    public List<FilterAttrBean> getFilter_attr() {
        return filter_attr;
    }

    public void setFilter_attr(List<FilterAttrBean> filter_attr) {
        this.filter_attr = filter_attr;
    }

    public static class FilterAttrBean {
        /**
         * name : 户型
         * item : [{"name":"户型","so_key":"door_door","so_name":"一居","so_value":"1","id":40,"type":6},{"name":"户型","so_key":"door_door","so_name":"二局","so_value":"2","id":41,"type":6},{"name":"户型","so_key":"door_door","so_name":"三局","so_value":"3","id":42,"type":6},{"name":"户型","so_key":"door_door","so_name":"四居","so_value":"4","id":43,"type":6},{"name":"户型","so_key":"door_door","so_name":"五局","so_value":"5","id":44,"type":6},{"name":"户型","so_key":"door_door","so_name":"六居以上","so_value":"6","id":45,"type":6},{"name":"户型","so_key":"door_door","so_name":"不限","so_value":"0","id":62,"type":6}]
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

        public static class ItemBean {
            /**
             * name : 户型
             * so_key : door_door
             * so_name : 一居
             * so_value : 1
             * id : 40
             * type : 6
             */

            private String name;
            private String so_key;
            private String so_name;
            private String so_value;
            private int id;
            private int type;

            public ItemBean() {
            }

            public ItemBean(String so_name) {
                this.so_name = so_name;
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
