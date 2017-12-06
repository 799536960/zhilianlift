package com.duma.ld.zhilianlift.view.main.city;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.SpDataUtil;

import java.util.List;

import me.yokeyword.indexablerv.IndexableHeaderAdapter;

import static com.duma.ld.zhilianlift.util.Constants.locationString;

/**
 * Created by liudong on 2017/12/4.
 */

public class CityHeaderLocationAdapter extends IndexableHeaderAdapter<String> {
    private static final int TYPE = 1;
    private Activity activity;

    public CityHeaderLocationAdapter(Activity activity, List list) {
        super("定位", "定位", list);
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
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, final String entity) {
        VH vh = (VH) holder;
        vh.tv.setText(entity);
        vh.layout_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (entity.equals(locationString)) {
                    return;
                }
                if (SpDataUtil.isCity(entity)) {
                    activity.finish();
                    return;
                }
                SpDataUtil.setCity(entity);
                EventBusUtil.sendModel(Constants.event_select_city);
                activity.finish();
            }
        });
    }


    private class VH extends RecyclerView.ViewHolder {
        private TextView tv;
        private LinearLayout layout_location;

        public VH(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_location);
            layout_location = itemView.findViewById(R.id.layout_location);
        }
    }
}
