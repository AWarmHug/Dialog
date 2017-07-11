package com.warm.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 作者: 51hs_android
 * 时间: 2017/7/10
 * 简介:
 */

public class MyDialogFragment extends BaseDialogFragment {

    public static final String PromotionBean = "PromotionBean";


    public static MyDialogFragment newInstance() {

        Bundle args = new Bundle();
        MyDialogFragment fragment = new MyDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onMyCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onSetWindow(Window window) {
        super.onSetWindow(window);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.TOP);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处,如果不设置会有边框或者在style中设置
    }


}
