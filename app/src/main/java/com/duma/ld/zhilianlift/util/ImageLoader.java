package com.duma.ld.zhilianlift.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.MyApplication;

/**
 * Created by 79953 on 2016/7/29.
 */
public class ImageLoader {
    public static void with(Context context, String url, ImageView imageView) {
        if (url != null && !url.contains("http")) {
            url = HttpUrl.BaseUrl + url;
        }
        RequestOptions options = new RequestOptions();
        //options.placeholder(loadingRes)//加载中图片
        //options.error(errorRes)//加载错误的图片
        options.error(R.color.hui1);//或者是个颜色值
        //options.circleCrop()设置成圆形头像<这个是V4.0新增的>
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void with(String url, ImageView imageView) {
        with(MyApplication.getInstance(), url, imageView);
    }
}
