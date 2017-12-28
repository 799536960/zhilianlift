package com.duma.ld.zhilianlift.model;

import java.util.List;

public class GoodsSpecListBean {
    /**
     * spec_name : 颜色
     * spec_list : [{"item_id":104,"item":"象牙白","src":null}]
     */
    //选中的规格position 默认0
    private int select;
    private String spec_name;
    private List<SpecListBean> spec_list;

    public GoodsSpecListBean() {
        //默认未选中
        select = -1;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public String getSpec_name() {
        return spec_name;
    }

    public void setSpec_name(String spec_name) {
        this.spec_name = spec_name;
    }

    public List<SpecListBean> getSpec_list() {
        return spec_list;
    }

    public void setSpec_list(List<SpecListBean> spec_list) {
        this.spec_list = spec_list;
    }

    public static class SpecListBean {
        /**
         * item_id : 104
         * item : 象牙白
         * src : null
         */

        private int item_id;
        private String item;
        private boolean isSelect;
        private int upPosition;
        private int thisPosition;

        public int getUpPosition() {
            return upPosition;
        }

        public void setUpPosition(int upPosition) {
            this.upPosition = upPosition;
        }

        public int getThisPosition() {
            return thisPosition;
        }

        public void setThisPosition(int thisPosition) {
            this.thisPosition = thisPosition;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }
    }
}