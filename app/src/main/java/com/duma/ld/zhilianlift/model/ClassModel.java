package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/12/4.
 */

public class ClassModel {

    /**
     * id : 6
     * name : 个人化妆
     * mobile_name : 个人化妆
     * parent_id : 0
     * parent_id_path : 0_6
     * level : 1
     * sort_order : 52
     * is_show : 1
     * image : /public/upload/category/2016/04-22/5719c4a1e3447.jpg
     * is_hot : 0
     * cat_group : 0
     * commission : 0
     * commission_rate : 0
     * type_id : 0
     * is_on_goods : 0
     * list : [{"mobile_name":"身体护肤","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":42,"level":2,"parent_id":6,"sub_category":[{"mobile_name":"沐浴","image":"","id":402,"level":3,"parent_id":42},{"mobile_name":"润肤","image":"","id":403,"level":3,"parent_id":42},{"mobile_name":"颈部","image":"","id":404,"level":3,"parent_id":42},{"mobile_name":"手足","image":"","id":405,"level":3,"parent_id":42},{"mobile_name":"纤体塑形","image":"","id":406,"level":3,"parent_id":42},{"mobile_name":"美胸","image":"","id":407,"level":3,"parent_id":42},{"mobile_name":"套装","image":"","id":408,"level":3,"parent_id":42}]},{"mobile_name":"口腔护理","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":43,"level":2,"parent_id":6,"sub_category":[{"mobile_name":"牙膏/牙粉","image":"","id":409,"level":3,"parent_id":43},{"mobile_name":"牙刷/牙线","image":"","id":410,"level":3,"parent_id":43},{"mobile_name":"漱口水","image":"","id":411,"level":3,"parent_id":43},{"mobile_name":"套装","image":"","id":412,"level":3,"parent_id":43}]},{"mobile_name":"女性护理","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":44,"level":2,"parent_id":6,"sub_category":[{"mobile_name":"卫生巾","image":"","id":413,"level":3,"parent_id":44},{"mobile_name":"卫生护垫","image":"","id":414,"level":3,"parent_id":44},{"mobile_name":"私密护理","image":"","id":415,"level":3,"parent_id":44},{"mobile_name":"脱毛膏","image":"","id":416,"level":3,"parent_id":44}]},{"mobile_name":"香水彩妆","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":45,"level":2,"parent_id":6,"sub_category":[{"mobile_name":"唇部","image":"","id":417,"level":3,"parent_id":45},{"mobile_name":"美甲","image":"","id":418,"level":3,"parent_id":45},{"mobile_name":"美容工具","image":"","id":419,"level":3,"parent_id":45},{"mobile_name":"套装","image":"","id":420,"level":3,"parent_id":45},{"mobile_name":"香水","image":"","id":421,"level":3,"parent_id":45},{"mobile_name":"底妆","image":"","id":422,"level":3,"parent_id":45},{"mobile_name":"腮红","image":"","id":423,"level":3,"parent_id":45},{"mobile_name":"眼部","image":"","id":424,"level":3,"parent_id":45}]},{"mobile_name":"清洁用品","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":46,"level":2,"parent_id":6,"sub_category":[{"mobile_name":"纸品湿巾","image":"","id":95,"level":3,"parent_id":46},{"mobile_name":"衣物清洁","image":"","id":96,"level":3,"parent_id":46},{"mobile_name":"家庭清洁","image":"","id":97,"level":3,"parent_id":46},{"mobile_name":"一次性用品","image":"","id":98,"level":3,"parent_id":46},{"mobile_name":"驱虫用品","image":"","id":99,"level":3,"parent_id":46}]},{"mobile_name":"面部护肤","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":47,"level":2,"parent_id":6,"sub_category":[{"mobile_name":"面膜","image":"","id":425,"level":3,"parent_id":47},{"mobile_name":"剃须","image":"","id":426,"level":3,"parent_id":47},{"mobile_name":"套装","image":"","id":427,"level":3,"parent_id":47},{"mobile_name":"清洁","image":"","id":428,"level":3,"parent_id":47},{"mobile_name":"护肤","image":"","id":429,"level":3,"parent_id":47}]},{"mobile_name":"洗发护发","image":"/public/upload/category/2016/04-02/56ffa28b12f4f.jpg","id":48,"level":2,"parent_id":6,"sub_category":[{"mobile_name":"套装","image":"","id":430,"level":3,"parent_id":48},{"mobile_name":"洗发","image":"","id":431,"level":3,"parent_id":48},{"mobile_name":"护发","image":"","id":432,"level":3,"parent_id":48},{"mobile_name":"染发","image":"","id":433,"level":3,"parent_id":48},{"mobile_name":"造型","image":"","id":434,"level":3,"parent_id":48},{"mobile_name":"假发","image":"","id":435,"level":3,"parent_id":48}]}]
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
         * sub_category : [{"mobile_name":"沐浴","image":"","id":402,"level":3,"parent_id":42},{"mobile_name":"润肤","image":"","id":403,"level":3,"parent_id":42},{"mobile_name":"颈部","image":"","id":404,"level":3,"parent_id":42},{"mobile_name":"手足","image":"","id":405,"level":3,"parent_id":42},{"mobile_name":"纤体塑形","image":"","id":406,"level":3,"parent_id":42},{"mobile_name":"美胸","image":"","id":407,"level":3,"parent_id":42},{"mobile_name":"套装","image":"","id":408,"level":3,"parent_id":42}]
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
             * mobile_name : 沐浴
             * image :
             * id : 402
             * level : 3
             * parent_id : 42
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
}
