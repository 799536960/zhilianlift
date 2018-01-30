package com.duma.ld.zhilianlift.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.IntentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudong on 2017/12/25.
 */

public class GridLayout extends LinearLayout implements View.OnClickListener {
    private Context context;
    private List<String> mList;
    private Activity activity;

    private LinearLayout layout_imgs1, layout_imgs2, layout_imgs3;
    private ImageView img_view_img1, img_view_img2, img_view_img3, img_view_img4, img_view_img5, img_view_img6, img_view_img7, img_view_img8, img_view_img9;

    public GridLayout(Context context) {
        this(context, null);
    }

    public GridLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View.inflate(context, R.layout.view_grid_layout, this);
        layout_imgs1 = findViewById(R.id.layout_imgs1);
        layout_imgs2 = findViewById(R.id.layout_imgs2);
        layout_imgs3 = findViewById(R.id.layout_imgs3);
        img_view_img1 = findViewById(R.id.img_view_img1);
        img_view_img2 = findViewById(R.id.img_view_img2);
        img_view_img3 = findViewById(R.id.img_view_img3);
        img_view_img4 = findViewById(R.id.img_view_img4);
        img_view_img5 = findViewById(R.id.img_view_img5);
        img_view_img6 = findViewById(R.id.img_view_img6);
        img_view_img7 = findViewById(R.id.img_view_img7);
        img_view_img8 = findViewById(R.id.img_view_img8);
        img_view_img9 = findViewById(R.id.img_view_img9);
        img_view_img1.setOnClickListener(this);
        img_view_img2.setOnClickListener(this);
        img_view_img3.setOnClickListener(this);
        img_view_img4.setOnClickListener(this);
        img_view_img5.setOnClickListener(this);
        img_view_img6.setOnClickListener(this);
        img_view_img7.setOnClickListener(this);
        img_view_img8.setOnClickListener(this);
        img_view_img9.setOnClickListener(this);
    }

    public void setmList(List<String> mList, Activity activity) {
        this.activity = activity;
        if (mList == null) {
            mList = new ArrayList<>();
        }
        this.mList = mList;
        layout_imgs1.setVisibility(GONE);
        layout_imgs2.setVisibility(GONE);
        layout_imgs3.setVisibility(GONE);
        int size = mList.size();
        if (size > 0 && size <= 3) {
            layout_imgs1.setVisibility(VISIBLE);
        } else if (size > 3 && size <= 6) {
            layout_imgs1.setVisibility(VISIBLE);
            layout_imgs2.setVisibility(VISIBLE);
        } else if (size > 6) {
            layout_imgs1.setVisibility(VISIBLE);
            layout_imgs2.setVisibility(VISIBLE);
            layout_imgs3.setVisibility(VISIBLE);
        }
        img_view_img1.setVisibility(INVISIBLE);
        img_view_img2.setVisibility(INVISIBLE);
        img_view_img3.setVisibility(INVISIBLE);
        img_view_img4.setVisibility(INVISIBLE);
        img_view_img5.setVisibility(INVISIBLE);
        img_view_img6.setVisibility(INVISIBLE);
        img_view_img7.setVisibility(INVISIBLE);
        img_view_img8.setVisibility(INVISIBLE);
        img_view_img9.setVisibility(INVISIBLE);
        for (int i = 0; i < mList.size(); i++) {
            switch (i) {
                case 0:
                    img_view_img1.setVisibility(View.VISIBLE);
                    ImageLoader.with(mList.get(i), img_view_img1);
                    break;
                case 1:
                    img_view_img2.setVisibility(View.VISIBLE);
                    ImageLoader.with(mList.get(i), img_view_img2);
                    break;
                case 2:
                    img_view_img3.setVisibility(View.VISIBLE);
                    ImageLoader.with(mList.get(i), img_view_img3);
                    break;
                case 3:
                    img_view_img4.setVisibility(View.VISIBLE);
                    ImageLoader.with(mList.get(i), img_view_img4);
                    break;
                case 4:
                    img_view_img5.setVisibility(View.VISIBLE);
                    ImageLoader.with(mList.get(i), img_view_img5);
                    break;
                case 5:
                    img_view_img6.setVisibility(View.VISIBLE);
                    ImageLoader.with(mList.get(i), img_view_img6);
                    break;
                case 6:
                    img_view_img7.setVisibility(View.VISIBLE);
                    ImageLoader.with(mList.get(i), img_view_img7);
                    break;
                case 7:
                    img_view_img8.setVisibility(View.VISIBLE);
                    ImageLoader.with(mList.get(i), img_view_img8);
                    break;
                case 8:
                    img_view_img9.setVisibility(View.VISIBLE);
                    ImageLoader.with(mList.get(i), img_view_img9);
                    break;
            }
        }
    }


    @Override
    public void onClick(View view) {
        int position = 0;
        switch (view.getId()) {
            case R.id.img_view_img1:
                position = 0;
                break;
            case R.id.img_view_img2:
                position = 1;
                break;
            case R.id.img_view_img3:
                position = 2;
                break;
            case R.id.img_view_img4:
                position = 3;
                break;
            case R.id.img_view_img5:
                position = 4;
                break;
            case R.id.img_view_img6:
                position = 5;
                break;
            case R.id.img_view_img7:
                position = 6;
                break;
            case R.id.img_view_img8:
                position = 7;
                break;
            case R.id.img_view_img9:
                position = 8;
                break;
        }
        starPhoto(position);
    }

    private void starPhoto(int position) {
        if (position > mList.size()) {
            return;
        }
        IntentUtil.goPhoto(activity, mList, position);
    }
}
