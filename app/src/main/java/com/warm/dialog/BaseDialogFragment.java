package com.warm.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public abstract class BaseDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog_Transparent);
    }

    /**
     * 我个人推荐在这里设置View,就像普通的Fragment一样，不要在{@link #onCreateDialog(Bundle)}中设置
     */
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * 获取Dialog的Window ,在获取到{@link Window#getDecorView()}，在这上面绘制界面，绘制结束后，设置Window相关内容，必须要按顺序
         * 还有一点需要注意,一定要设置BackgroundDrawable，否则会有边框
         * 或者在style中设置 <item name="android:windowBackground">@android:color/transparent</item>
         * window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         */
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
