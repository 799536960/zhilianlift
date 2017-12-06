package com.duma.ld.zhilianlift.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudong on 2017/12/6.
 */

public class HotModel implements Serializable {
    private List<String> hot_keywords;

    public List<String> getHot_keywords() {
        return hot_keywords;
    }

    public void setHot_keywords(List<String> hot_keywords) {
        this.hot_keywords = hot_keywords;
    }
}
