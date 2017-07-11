package com.warm.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * 作者: 51hs_android
 * 时间: 2017/7/11
 * 简介:
 */

public abstract class FullDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.AppTheme_Dialog_Transparent);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Window window = getDialog().getWindow();
        View view = onMyCreateView(inflater, window != null ? (ViewGroup) window.getDecorView() : container, savedInstanceState);
        onSetWindow(window);

        return view;
    }

    public abstract View onMyCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 设置window相关。
     * {@link Window#setWindowAnimations(int)}
     * {@link Window#setGravity(int)} (int)}
     * {@link Window#setLayout(int, int)} (int)}
     * 等等
     *
     * @param window
     */
    public void onSetWindow(Window window) {

    }

}
