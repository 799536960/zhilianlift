package com.duma.ld.zhilianlift.view.start;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.Adapter.QueryPhotoAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.util.Constants;

import java.util.List;

import butterknife.BindView;


public class PhotoQueryActivity extends BaseMyActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.pager_photo)
    ViewPager pagerPhoto;
    @BindView(R.id.tv_allNum)
    TextView tvAllNum;

    private List<String> mList;
    private int position;
    private QueryPhotoAdapter adapter;


    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_photoquery, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mList = (List<String>) getIntent().getSerializableExtra(Constants.Model);
        position = Integer.parseInt(getIntent().getStringExtra(Constants.key));
        tvPosition.setText((position + 1) + "");
        tvAllNum.setText("/" + mList.size());
        adapter = new QueryPhotoAdapter(mList, mActivity);
        pagerPhoto.setAdapter(adapter);
        pagerPhoto.setCurrentItem(position);
        pagerPhoto.addOnPageChangeListener(this);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.a3);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tvPosition.setText((position + 1) + "");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
