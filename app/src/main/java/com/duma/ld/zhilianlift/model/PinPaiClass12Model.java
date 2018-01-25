package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/25.
 */

public class PinPaiClass12Model {

    /**
     * id : 6
     * name : 个人化妆
     * mobile_name : 个人化妆
     * parent_id : 0
     * parent_id_path : 0_6
     * level : 1
     * sort_order : 52
     * is_show : 1
     * image : /public/upload/category/2017/12-05/5f63213bf76320fa08a2afe43cc0746a.png
     * is_hot : 1
     * cat_group : 0
     * commission : 0
     * commission_rate : 0
     * type_id : 0
     * is_on_goods : 0
     * url : http://192.168.0.58:8081/index.php/mobile/Goods/goodsInfo/id/140.html
     * list : [{"mobile_name":"身体护肤","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":42,"level":2,"parent_id":6},{"mobile_name":"口腔护理","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":43,"level":2,"parent_id":6},{"mobile_name":"女性护理","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":44,"level":2,"parent_id":6},{"mobile_name":"香水彩妆","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":45,"level":2,"parent_id":6},{"mobile_name":"清洁用品","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":46,"level":2,"parent_id":6},{"mobile_name":"面部护肤","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":47,"level":2,"parent_id":6},{"mobile_name":"洗发护发","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":48,"level":2,"parent_id":6}]
     */

    private int id;
    private String name;
    private String mobile_name;
    private int parent_id;
    private String parent_id_path;
    private int level;
    private int sort_order;
    private int is_show;
    private String image;
    private int is_hot;
    private int cat_group;
    private int commission;
    private int commission_rate;
    private int type_id;
    private int is_on_goods;
    private String url;
    private List<ListBean> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_name() {
        return mobile_name;
    }

    public void setMobile_name(String mobile_name) {
        this.mobile_name = mobile_name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_id_path() {
        return parent_id_path;
    }

    public void setParent_id_path(String parent_id_path) {
        this.parent_id_path = parent_id_path;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }

    public int getIs_show() {
        return is_show;
    }

    public void setIs_show(int is_show) {
        this.is_show = is_show;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    public int getCat_group() {
        return cat_group;
    }

    public void setCat_group(int cat_group) {
        this.cat_group = cat_group;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getCommission_rate() {
        return commission_rate;
    }

    public void setCommission_rate(int commission_rate) {
        this.commission_rate = commission_rate;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getIs_on_goods() {
        return is_on_goods;
    }

    public void setIs_on_goods(int is_on_goods) {
        this.is_on_goods = is_on_goods;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * mobile_name : 身体护肤
         * image : /public/upload/category/2016/04-02/56ffa28b12f4f.jpg
         * id : 42
         * level : 2
         * parent_id : 6
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
