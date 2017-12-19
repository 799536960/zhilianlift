package com.duma.ld.zhilianlift.view.main.city;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.model.CityHeaderModel;
import com.duma.ld.zhilianlift.model.QuModel;
import com.duma.ld.zhilianlift.util.SpDataUtil;

import java.util.List;

import me.yokeyword.indexablerv.IndexableHeaderAdapter;

/**
 * Created by liudong on 2017/12/4.
 */

public class CityHeaderRvAdapter extends IndexableHeaderAdapter<CityHeaderModel> {
    private static final int TYPE = 2;
    private Activity activity;
    private boolean isOpen = true;
    private onHttpRes onHttpRes;

    public interface onHttpRes {
        void httpRes();
    }

    public CityHeaderRvAdapter(Activity activity, List<CityHeaderModel> list, CityHeaderRvAdapter.onHttpRes onHttpRes) {
        super("当前", null, list);
        this.activity = activity;
        this.onHttpRes = onHttpRes;
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        return new VH(LayoutInflater.from(activity).inflate(R.layout.item_top_rv, parent, false));
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, final CityHeaderModel entity) {
        final VH vh = (VH) holder;
        vh.tv_city.setText("当前:" + SpDataUtil.getCity());
        vh.layout_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (entity.getCity() == null || entity.getCity().size() == 0) {
                    //没有数据 去请求
                    onHttpRes.httpRes();
                    return;
                }
                vh.img_arrow.animate().rotationBy(180);
                isOpen = !isOpen;
                if (isOpen) {
                    vh.rv_list.setVisibility(View.VISIBLE);
                } else {
                    vh.rv_list.setVisibility(View.GONE);
                }
            }
        });
        if (entity.isOne()) {
            vh.img_arrow.animate().rotationBy(180);
            entity.setOne(false);
            isOpen = true;
            vh.rv_list.setVisibility(View.VISIBLE);
        }


        BaseAdapter<QuModel> adapter = new BaseAdapter<QuModel>(R.layout.adapter_cityheader) {
            @Override
            protected void convert(BaseViewHolder helper, final QuModel item) {
                helper.setText(R.id.tv_location, item.getName());
                helper.setOnClickListener(R.id.layout_location, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.finish();
                        SpDataUtil.setDistrict(item.getName(), item.getCode());
                    }
                });
            }
        };
        vh.rv_list.setFocusable(false);
        vh.rv_list.setNestedScrollingEnabled(false);
        vh.rv_list.setLayoutManager(new GridLayoutManager(activity, 3));
        vh.rv_list.setAdapter(adapter);
        adapter.setNewData(entity.getCity());

    }


    private class VH extends RecyclerView.ViewHolder {
        private RecyclerView rv_list;
        private LinearLayout layout_switch;
        private ImageView img_arrow;
        private TextView tv_city;

        public VH(View itemView) {
            super(itemView);
            rv_list = itemView.findViewById(R.id.rv_list);
            layout_switch = itemView.findViewById(R.id.layout_switch);
            img_arrow = itemView.findViewById(R.id.img_arrow);
            tv_city = itemView.findViewById(R.id.tv_city);
        }
    }
}
