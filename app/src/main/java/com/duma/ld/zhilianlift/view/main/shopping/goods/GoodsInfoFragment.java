package com.duma.ld.zhilianlift.view.main.shopping.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.blankj.utilcode.util.EncodeUtils;
import com.duma.ld.baselibrary.util.TsUtils;
import com.duma.ld.baselibrary.util.config.FragmentConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyFragment;
import com.duma.ld.zhilianlift.model.GoodsInfoModel;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.HttpUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

import static com.duma.ld.zhilianlift.util.HttpUrl.goodsContent;

/**
 * 商品详情
 * Created by liudong on 2017/12/22.
 */

public class GoodsInfoFragment extends BaseMyFragment {

    @BindView(R.id.webView_content)
    WebView webViewContent;
    private String id;

    public static GoodsInfoFragment newInstance(String id) {
        GoodsInfoFragment fragment = new GoodsInfoFragment();
        Bundle args = new Bundle();
        args.putString(Constants.id, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentConfig setFragmentConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByFragment(R.layout.fragment_goods_info);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString(Constants.id);
        } else {
            TsUtils.show("商品id获取失败!");
            mActivity.finish();
        }
        mFragmentConfig.showLoadingView();
        webViewContent.getSettings().setJavaScriptEnabled(false);
        webViewContent.getSettings().setSupportZoom(false);
        webViewContent.getSettings().setBuiltInZoomControls(false);
        webViewContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        onClickLoadingRefresh();
    }

    @Override
    public void onClickLoadingRefresh() {
        super.onClickLoadingRefresh();
        OkGo.<HttpResModel<GoodsInfoModel>>get(goodsContent)
                .params("id", id)
                .execute(new MyJsonCallback<HttpResModel<GoodsInfoModel>>(mFragmentConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<GoodsInfoModel>> respons, HttpResModel<GoodsInfoModel> goodsInfoModelHttpResModel) {
                        initData(goodsInfoModelHttpResModel.getResult());
                    }
                });
    }

    private void initData(GoodsInfoModel result) {
        String htmlData = EncodeUtils.htmlDecode(result.getGoods_content()).toString();
//        htmlData = htmlData.replace("<img", "<img style='max-width:90%;height:auto;margin:0 auto;display:block;'");
        htmlData = htmlData.replace("<img", "<img style='max-width:100%;height:auto;'");
        webViewContent.loadDataWithBaseURL(HttpUrl.BaseUrl, htmlData, "text/html", "utf-8", null);
    }
}
