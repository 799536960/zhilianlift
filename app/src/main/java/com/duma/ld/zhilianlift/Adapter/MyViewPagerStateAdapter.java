package com.duma.ld.zhilianlift.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerStateAdapter extends FragmentStatePagerAdapter {
    public List<Fragment> mFragments = new ArrayList<>();//添加的Fragment的集合
    public List<String> mFragmentsTitles = new ArrayList<>();//每个Fragment对应的title的集合


    public MyViewPagerStateAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * @param fragment      添加Fragment
     * @param fragmentTitle Fragment的标题，即TabLayout中对应Tab的标题
     */
    public void addFragment(Fragment fragment, String fragmentTitle) {
        mFragments.add(fragment);
        mFragmentsTitles.add(fragmentTitle);
    }

    public void setTitle(int position, String fragmentTitle) {
        if (mFragmentsTitles.size() >= position + 1) {
            mFragmentsTitles.set(position, fragmentTitle);
        }
    }

    public void setFragment(int position, Fragment fragment) {
        if (mFragments.size() >= position + 1) {
            mFragments.set(position, fragment);
        }
    }

    public void clearList() {
        mFragments.clear();
        mFragmentsTitles.clear();
    }

    @Override
    public Fragment getItem(int position) {
        //得到对应position的Fragment
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        //返回Fragment的数量
        return mFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //得到对应position的Fragment的title
        return mFragmentsTitles.get(position);
    }


}
