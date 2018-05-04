package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/5/4.
 */

public class PingPaiShaiXuanModel {

    /**
     * mobile_name : 全屋定制
     * image :
     * id : 1015
     * level : 2
     * parent_id : 872
     * sub_category : [{"mobile_name":"全屋家具","image":"/public/upload/category/2018/05-04/2a06929311f28806f2afc1b9e5dd43a7.png","id":1092,"level":3,"parent_id":1015},{"mobile_name":"全屋大理石","image":"/public/upload/category/2018/04-28/6cf60da813f9fce7ef3fd5164f940ec3.png","id":1016,"level":3,"parent_id":1015},{"mobile_name":"定制橱柜","image":"/public/upload/category/2018/04-28/3ef86a749c338544f8ae2aa030d73203.png","id":1017,"level":3,"parent_id":1015},{"mobile_name":"定制榻榻米","image":"/public/upload/category/2018/04-28/e99a80aead6bf6ad6247fef3d263f2fe.png","id":1018,"level":3,"parent_id":1015},{"mobile_name":"定制楼梯","image":"/public/upload/category/2018/04-28/1a56aab314cd7ca14848e0bfd37dc66d.png","id":1019,"level":3,"parent_id":1015}]
     */

    private String mobile_name;
    private String image;
    private int id;
    private int level;
    private int parent_id;
    private List<SubCategoryBean> sub_category;

    public String getMobile_name() {
        return mobile_name;
    }

    public void setMobile_name(String mobile_name) {
        this.mobile_name = mobile_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<SubCategoryBean> getSub_category() {
        return sub_category;
    }

    public void setSub_category(List<SubCategoryBean> sub_category) {
        this.sub_category = sub_category;
    }

    public static class SubCategoryBean {
        /**
         * mobile_name : 全屋家具
         * image : /public/upload/category/2018/05-04/2a06929311f28806f2afc1b9e5dd43a7.png
         * id : 1092
         * level : 3
         * parent_id : 1015
         */

        private String mobile_name;
        private String image;
        private int id;
        private int level;
        private int parent_id;

        public String getMobile_name() {
            return mobile_name;
        }

        public void setMobile_name(String mobile_name) {
            this.mobile_name = mobile_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }
    }
}
