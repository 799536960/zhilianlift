package com.duma.ld.zhilianlift.view.home.city;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.zhilianlift.R;

import java.util.List;

import me.yokeyword.indexablerv.IndexableHeaderAdapter;

/**
 * Created by liudong on 2017/12/4.
 */

public class CityHeaderLocationAdapter extends IndexableHeaderAdapter<String> {
    private static final int TYPE = 1;
    private Activity activity;

    public CityHeaderLocationAdapter(Activity activity, List list) {
        super("定位", null, list);
        this.activity = activity;
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        return new VH(LayoutInflater.from(activity).inflate(R.layout.item_location, parent, false));
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, String entity) {
        VH vh = (VH) holder;
        vh.tv.setText(entity);
        vh.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TsUtils.show("sss");
            }
        });
    }


    private class VH extends RecyclerView.ViewHolder {
        private TextView tv;

        public VH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_location);
        }
    }
}
