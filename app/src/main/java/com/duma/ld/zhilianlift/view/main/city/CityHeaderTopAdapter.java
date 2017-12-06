package com.duma.ld.zhilianlift.view.main.city;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duma.ld.zhilianlift.R;

import java.util.List;

import me.yokeyword.indexablerv.IndexableHeaderAdapter;

/**
 * Created by liudong on 2017/12/4.
 */

public class CityHeaderTopAdapter extends IndexableHeaderAdapter<String> {
    private static final int TYPE = 2;
    private Activity activity;

    public CityHeaderTopAdapter(Activity activity, List<String> list) {
        super("当前", null, list);
        this.activity = activity;
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        return new VH(LayoutInflater.from(activity).inflate(R.layout.item_top, parent, false));
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, String entity) {
        final VH vh = (VH) holder;
        vh.layout_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vh.img_arrow.animate().rotationBy(180);
            }
        });
    }


    private class VH extends RecyclerView.ViewHolder {
        private LinearLayout layout_switch;
        private ImageView img_arrow;

        public VH(View itemView) {
            super(itemView);
            layout_switch = itemView.findViewById(R.id.layout_switch);
            img_arrow = itemView.findViewById(R.id.img_arrow);
        }
    }
}
