package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseAdapter.BaseAdapter;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.ProvinceModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

/**
 * Created by liudong on 2017/12/12.
 */

public class SelectAddresFragment extends BaseMyFragment {

    @BindView(R.id.tv_list)
    RecyclerView tvList;
    private String url;

    private BaseAdapter<ProvinceModel> adapter;

    public static SelectAddresFragment newInstance(String url) {
        SelectAddresFragment fragment = new SelectAddresFragment();
        Bundle args = new Bundle();
        args.putString(Constants.Url, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        Bundle args = getArguments();
        if (args != null) {
            url = args.getString(Constants.Url);
        }
        return initConfig.setLayoutIdByFragment(R.layout.fragment_select_addres);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        adapter = new BaseAdapter<ProvinceModel>(R.layout.adapter_select_addres) {
            @Override
            protected void convert(BaseViewHolder helper, ProvinceModel item) {

            }
        };
        tvList.setLayoutManager(new LinearLayoutManager(mActivity));
        tvList.setAdapter(adapter);
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<List<ProvinceModel>>>get(url)
                .execute(new MyJsonCallback<HttpResModel<List<ProvinceModel>>>(mFragmentConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<ProvinceModel>>> respons, HttpResModel<List<ProvinceModel>> listHttpResModel) {
                        adapter.setNewData(listHttpResModel.getResult());
                    }
                });
    }

}
