package com.duma.ld.zhilianlift.view.main.wode.addres;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duma.ld.zhilianlift.R;

/**
 * Created by liudong on 2017/12/12.
 */

public class DemoFramgnet extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.demo_rv, container, false);
    }
}
