package com.duma.ld.zhilianlift.view.home.city;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.CityEntity;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.SpDataUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Demo: 搜索结果显示Fragment
 * Created by YoKey on 16/10/9.
 */
public class SearchFragment extends BaseMyFragment {
    @BindView(R.id.recy)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_no_result)
    TextView tvNoResult;
    private SearchAdapter mAdapter;
    private List<CityEntity> mDatas;
    private String mQueryText;


    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_search_city, false);
    }

    public void bindDatas(List<CityEntity> datas) {
        this.mDatas = datas;
        mAdapter = new SearchAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        if (mQueryText != null) {
            mAdapter.getFilter().filter(mQueryText);
        }
    }

    /**
     * 根据newText 进行查找, 显示
     */
    public void bindQueryText(String newText) {
        if (mDatas == null) {
            mQueryText = newText.toLowerCase();
        } else if (!TextUtils.isEmpty(newText)) {
            mAdapter.getFilter().filter(newText.toLowerCase());
        }
    }


    private class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.VH> implements Filterable {
        private List<CityEntity> items = new ArrayList<>();

        public SearchAdapter() {
            items.clear();
            items.addAll(mDatas);
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            final VH holder = new VH(LayoutInflater.from(getActivity()).inflate(R.layout.item_city, parent, false));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    if (SpDataUtil.isCity(items.get(position).getName())) {
                        mActivity.finish();
                        return;
                    }
                    SpDataUtil.setCity(items.get(position).getName());
                    EventBusUtil.sendModel(Constants.event_select_city);
                    mActivity.finish();
                }
            });
            return holder;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.tvName.setText(items.get(position).getName());
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    ArrayList<CityEntity> list = new ArrayList<>();
                    for (CityEntity item : mDatas) {
                        if (item.getPinyin().startsWith(constraint.toString()) || item.getName().contains(constraint)) {
                            list.add(item);
                        }
                    }
                    FilterResults results = new FilterResults();
                    results.count = list.size();
                    results.values = list;
                    return results;
                }

                @Override
                @SuppressWarnings("unchecked")
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    ArrayList<CityEntity> list = (ArrayList<CityEntity>) results.values;
                    items.clear();
                    items.addAll(list);
                    if (results.count == 0) {
                        tvNoResult.setVisibility(View.VISIBLE);
                    } else {
                        tvNoResult.setVisibility(View.INVISIBLE);
                    }
                    notifyDataSetChanged();
                }
            };
        }

        class VH extends RecyclerView.ViewHolder {
            private TextView tvName;

            public VH(View itemView) {
                super(itemView);
                tvName = (TextView) itemView.findViewById(R.id.tv_name);
            }
        }
    }
}
