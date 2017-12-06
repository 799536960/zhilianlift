package com.duma.ld.zhilianlift.util;


import com.duma.ld.zhilianlift.model.CacheModel;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by liudong on 2017/12/6.
 */

public class CacheUtil {
    /**
     * 缓存查询
     */
    public static List<CacheModel> queryAll() {
        return DataSupport.order("id desc").find(CacheModel.class);
    }

    //是否有缓存
    public static boolean isCache() {
        List<CacheModel> cacheUtils = queryAll();
        if (cacheUtils != null && cacheUtils.size() > 0) {
            return true;
        }
        return false;
    }

    public static void addCache(String cache) {
        DataSupport.deleteAll(CacheModel.class, "content = ?", cache);
        CacheModel model = new CacheModel(cache);
        model.save();
    }

    public static void deleteAll() {
        DataSupport.deleteAll(CacheModel.class);
    }
}
