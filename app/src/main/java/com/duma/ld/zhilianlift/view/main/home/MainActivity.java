package com.duma.ld.zhilianlift.view.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.duma.ld.baselibrary.util.config.ActivityConfig;
import com.duma.ld.baselibrary.util.config.InitConfig;
import com.duma.ld.zhilianlift.R;
import com.duma.ld.zhilianlift.base.baseJsonHttp.MyJsonCallback;
import com.duma.ld.zhilianlift.base.baseView.BaseMyActivity;
import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.UserModel;
import com.duma.ld.zhilianlift.util.Constants;
import com.duma.ld.zhilianlift.util.IntentUtil;
import com.duma.ld.zhilianlift.util.SpDataUtil;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.duma.ld.zhilianlift.util.HttpUrl.userInfo;


/**
 * 首页
 * Created by liudong on 2017/11/29.
 */
public class MainActivity extends BaseMyActivity {
    @BindView(R.id.layout_fragment)
    FrameLayout layoutFragment;
    @BindView(R.id.bar_bottom)
    BottomNavigationViewEx barBottom;

    private SupportFragment[] mFragments = new SupportFragment[5];
    private Badge badge;

    @Override
    protected ActivityConfig setActivityConfig(Bundle savedInstanceState, InitConfig initConfig) {
        setTheme(R.style.AppTheme);
        return initConfig.setLayoutIdByActivity(R.layout.activity_home, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreYuanDIan();
    }

    public void refreYuanDIan() {
        UserModel user = SpDataUtil.getUser();
        if (user != null) {
            OkGo.<HttpResModel<UserModel>>get(userInfo)
                    .tag(httpTag)
                    .execute(new MyJsonCallback<HttpResModel<UserModel>>() {
                        @Override
                        protected void onJsonSuccess(Response<HttpResModel<UserModel>> respons, HttpResModel<UserModel> userModelHttpResModel) {
                            SpDataUtil.setUser(userModelHttpResModel.getResult());
                            try {
                                addBadgeAt(3, Integer.parseInt(userModelHttpResModel.getResult().getCart_goods_num()));
                            } catch (NumberFormatException e) {
                                Logger.e("小圆点错误");
                            }
                        }

                        @Override
                        public void onError(Response<HttpResModel<UserModel>> response) {
                            super.onError(response);
                        }
                    });
        }
    }

    private void addBadgeAt(int position, int number) {
        if (badge == null) {
            // add badge
            badge = new QBadgeView(this)
                    .setGravityOffset(12, 2, true)
                    .bindTarget(barBottom.getBottomNavigationItemView(position));
        }
        badge.setBadgeNumber(number);
        if (number == 0) {
            badge
                    .hide(true);
        }
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
    }

    private void initFragment() {
        if (findFragment(HomeFragment.class) == null) {
            mFragments[0] = new HomeFragment();
            mFragments[1] = new ClassFragment();
            mFragments[2] = new FinanceFragment();
            mFragments[3] = new ShoppingCartFragment();
            mFragments[4] = new MyFragment();
            loadMultipleRootFragment(R.id.layout_fragment, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3],
                    mFragments[4]);
        } else {
            mFragments[0] = findFragment(HomeFragment.class);
            mFragments[1] = findFragment(ClassFragment.class);
            mFragments[2] = findFragment(FinanceFragment.class);
            mFragments[3] = findFragment(ShoppingCartFragment.class);
            mFragments[4] = findFragment(MyFragment.class);
        }

        barBottom.enableAnimation(true);
        barBottom.enableShiftingMode(false);
        barBottom.enableItemShiftingMode(false);
        barBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.i_home:
                        showHideFragment(mFragments[0]);
                        break;
                    case R.id.i_class:
                        showHideFragment(mFragments[1]);
                        break;
                    case R.id.i_finance:
                        showHideFragment(mFragments[2]);
                        break;
                    case R.id.i_shoppingCart:
                        if (SpDataUtil.isLogin()) {
                            showHideFragment(mFragments[3]);
                            return true;
                        } else {
                            IntentUtil.goLogin(mActivity);
                            return false;
                        }
                    case R.id.i_my:
                        showHideFragment(mFragments[4]);
                        break;
                }
                return true;
            }
        });
    }

    public void showFinance() {
        barBottom.setCurrentItem(2);
    }

    public void showClass() {
        barBottom.setCurrentItem(1);
    }

    @Override
    protected void onBack() {
        if (System.currentTimeMillis() - Constants.TOUCH_TIME < Constants.WAIT_TIME) {
            finish();
        } else {
            Constants.TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出App", Toast.LENGTH_SHORT).show();
        }
    }

}
