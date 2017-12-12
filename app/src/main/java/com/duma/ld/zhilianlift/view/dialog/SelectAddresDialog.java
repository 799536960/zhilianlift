package com.duma.ld.zhilianlift.view.dialog;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.duma.ld.baselibrary.base.BaseActivity;
import com.duma.ld.zhilianlift.Adapter.MyViewPagerAdapter;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseView.BaseDownDialog;
import com.duma.ld.zhilianlift.view.main.wode.addres.SelectAddresFragment;

/**
 * Created by liudong on 2017/12/12.
 */

public class SelectAddresDialog extends BaseDownDialog {
    private FrameLayout layout_back;
    private TabLayout tabLayout_addres;
    private ViewPager viewPager_addres;

    public SelectAddresDialog(@NonNull BaseActivity context) {
        super(context);
    }

    @Override
    protected void initView() {
        layout_back = findViewById(R.id.layout_back);
        tabLayout_addres = findViewById(R.id.tabLayout_addres);
        viewPager_addres = findViewById(R.id.viewPager_addres);
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(mBaseActivity.getSupportFragmentManager());
        viewPagerAdapter.addFragment(new SelectAddresFragment(), "抢车位");
        viewPager_addres.setAdapter(viewPagerAdapter);
        tabLayout_addres.setupWithViewPager(viewPager_addres);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_select_addres;
    }
}
