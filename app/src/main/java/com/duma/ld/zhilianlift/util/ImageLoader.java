package com.duma.ld.zhilianlift.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.MyApplication;

/**
 * 图片加载
 * Created by 79953 on 2016/7/29.
 */
public class ImageLoader {
    public static void with(Object url, ImageView imageView) {
        with(url, imageView, 0);
    }

    public static void with(Object url, ImageView imageView, int roundingRadius) {
        RequestOptions options = getDifautImg();
        if (roundingRadius != 0) {
            options = options.centerCrop().transform(new RoundedCorners(roundingRadius));
        }
        loadImg(url, imageView, options);
    }


    public static void with_noCache(Object url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.color.hui3);
        loadImg(url, imageView, options);
    }

    public static void with_head(Object url, ImageView imageView) {
        RequestOptions options = getDifautImg()
                .circleCrop();
        loadImg(url, imageView, options);
    }

    private static void loadImg(Object url, ImageView imageView, RequestOptions options) {
        if (url != null) {
            if (url instanceof String) {
                if (!((String) url).contains("http")) {
                    url = HttpUrl.BaseUrl + url;
                }
            }
        }
        Glide.with(MyApplication.getInstance())
                .load(url)
                .apply(options)
                .into(imageView);
    }

    private static RequestOptions getDifautImg() {
        return new RequestOptions()
                .placeholder(R.color.hui3);
    }
}
