package com.duma.ld.zhilianlift.view.main.pay;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.PayStoreModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 向商家付款
 * Created by liudong on 2018/1/5.
 */

public class PayStoreActivity extends BaseMyActivity {
    @BindView(R.id.layout_fragment)
    FrameLayout layoutFragment;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        return initConfig.setLayoutIdByActivity(R.layout.activity_pay_store, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        PayStoreModel model = (PayStoreModel) getIntent().getSerializableExtra(Constants.Model);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.layout_fragment, ConfirmPayFragment.newInstance(model));
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.v_back_enter, R.anim.v_back_exit);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(mActivity);
    }

}
