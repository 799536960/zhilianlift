package com.duma.ld.zhilianlift.view.main.wode.realName;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.duma.ld.baselibrary.util.ZhuanHuanUtil;
import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.RealNameModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.ImageLoader;
import com.duma.ld.zhilianlift.util.SpDataUtil;

import butterknife.BindView;

/**
 * Created by liudong on 2017/12/14.
 */

public class RealNameMainActivity extends BaseMyActivity {
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_idCard)
    TextView tvIdCard;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_real_name_main, false).setTopBar_A("实名认证").setNoYinyin();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        RealNameModel model = (RealNameModel) getIntent().getSerializableExtra(Constants.Model);
        ImageLoader.with_head(mActivity, SpDataUtil.getUser().getHead_pic(), imgIcon);
        tvName.setText(model.getRealname());
        tvIdCard.setText(ZhuanHuanUtil.setIdCardXX(model.getIdcard()));
    }
}
