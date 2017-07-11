# Dialog
​	最近在写一个弹框，宽度需要占满整个屏幕。之前一直使用PopupWindow来做这样的事情，但是PopupWindow一直存在很多问题，背景不能直接变暗，从其他节目回到显示popupWindow的界面，会重新执行一遍显示动画，之前也对Popupwindow的这些问题进行过解决，但是最近有发现在Android7.0上PopupWindow出现了兼容性问题(具体可以看这里[PopupWindow 在 Android N(7.0) 的兼容性问题](http://www.jianshu.com/p/0df10893bf5b))。    

​	种种问题，想想还是用Dialog来显示，但是PopupWindow确实比Dialog要灵活很多，指哪放哪。  

​	我再网上看了很多别人写的博客，写的太碎，就是放了几个代码片段，设置一下Style,设置一下Dialog。写的实在太模糊了，很多并没有效果，所以 我就简单看了看源码，结合网上的一些说法，自己设置了一下。   
介于现在推荐使用DialogFragment（[为什么推荐使用DialogFragment](http://blog.csdn.net/lmj623565791/article/details/37815413/)），所以我也是基于DialogFragment介绍的。   

​	首先设置一下Style，很简单，一目了然:   

```xml
<style name="AppTheme.Dialog.Transparent" parent="android:Theme.Dialog">

    <!--设置全屏，去除stateBar-->
    <!--<item name="android:windowFullscreen">true</item>-->
    <!-- 背景透明 -->
    <item name="android:windowBackground">@android:color/transparent</item>
    <item name="android:windowContentOverlay">@null</item>
    <!-- 浮于Activity之上 -->
    <item name="android:windowIsFloating">true</item>
    <!-- 边框 -->
    <item name="android:windowFrame">@null</item>
    <!-- Dialog以外的区域模糊效果 -->
    <item name="android:backgroundDimEnabled">true</item>
    <!-- 无标题 -->
    <item name="android:windowNoTitle">true</item>
    <!-- 半透明 -->
    <item name="android:windowIsTranslucent">true</item>

</style>
```

​	然后，继承DialogFragment，主要内容都在注释中：

```java
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
```

​	最后，说明一下PopupWindow和Dialog的一些使用差异吧，都是使用WindowManager生成一个Window显示相关内容，但是PopupWindow，并没有创建一个Window的引用，所以，我们无法getWindow()进行修改背景浮层，大小，位置，所以PopupWindow封装了很多方法，来修改位置，所以很多人为了修改背景浮层，直接修改了activity上Window的alpha,类似这样：

```java
    WindowManager.LayoutParams lp = activity.getWindow()
            .getAttributes();
    lp.alpha = 0.4f;
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    activity.getWindow().setAttributes(lp);
```

但是我个人是不推荐的，因为很多机子上可能会出现问题，尤其是华为，而且还需要一个渐变的效果，就需要写一个动画不断修改alpha，还是在华为的一些机子（p8）上，这样的渐变，会变得很卡，完全不像是渐变效果，我之前在修改PopupWindow时，就简单封装了一下，我获取循环获取最外层的ViewGroup，或者直接获取Acivity上window的getDecorView()，在这上面添加一个半透明的View，执行一个渐变动画，就不会出现兼容问题了。因为Dialog是可以获取Window的，所有我们可以直接通过Dialog获取Window，设置相关宽高和Gravity等等。

​	如果Window不需要特意指定在某个控件附近，只是放在屏幕上、下、左、右，还是应该使用DialogFragment。