package cn.iven.mydemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import cn.iven.mydemo.MyApp;
import cn.iven.mydemo.Navigator;
import cn.iven.mydemo.R;
import cn.iven.mydemo.base.iface.IActivityPresenter;
import cn.iven.mydemo.base.iface.IActivityView;
import cn.iven.mydemo.base.iface.IDataProcess;
import cn.iven.mydemo.base.iface.ILoading;
import cn.iven.mydemo.dagger.component.MyAppComponent;
import cn.iven.mydemo.module.widget.dialog.LoadingDialogFragment;
import cn.iven.mydemo.module.widget.tips.DefaultTipsHelper;
import cn.iven.mydemo.module.widget.tips.TipsHelper;

/**
 * Created by iven on 2017/6/19.
 * * 基类封装
 * 1:是否开启eventbus事件总 重写方法initBuild() 开启参数 OPEN_EVENTBUS，
 * 开启后，必须提供eventbus方法的接收，否则报错。
 * <p>
 * 2:ButterKnife自动绑定，实现者不需要在上层做绑定，请使用工具ButterKnife Zelezny进行绑定。
 * <p>
 * 3:是否开启用全局loading..这个用处不大，用户体验不好
 * 使用者可以createLoadingDialog()方法，自己写一个全局loading,
 * 重写showLoading()、hideLoading()方法，在什么时候显示隐藏。
 * 请求数据会showLoading()，请求完成功或者失败都会调hideLoading()
 * <p>
 * 4:封装通用界面用户显示接口，需要使用者重写initTipsHelper()，网络连接错误界面、无数据界面、loading界面，
 * 默认提供了实现类DefaultTipsHelper，通过Builder模式构建。
 * <p>
 * 5：initComponent() dagger2注入
 * initUI() 界面控件配置，例如设置适配等。
 * initAnim() 初始动画设置
 * initData(savedInstanceState) 获取数据接口
 */

public abstract class BaseActivity<T> extends AppCompatActivity implements IActivityView,IDataProcess<T>, DefaultTipsHelper.OnClickTipListener {

    private static final int OPEN_DEFUALT = 0x0001;
    public static final int OPEN_EVENTBUS = OPEN_DEFUALT << 0; //开启事件总线
    public static final int OPEN_LOADING = OPEN_DEFUALT << 1;  //开启等待框
    public static final int OPEN_EVENTBUS_LOADING = OPEN_EVENTBUS | OPEN_LOADING;

    protected final String mTag = BaseActivity.class.getSimpleName();

    protected ILoading mILoading;

    @Inject
    protected Toast mToast;

    @Inject
    protected Navigator mNavigator;

    private int mMainBuild = 0;

    public void setBasePresenter(IActivityPresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    protected IActivityPresenter basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        mMainBuild = initBuild();
        initTipsHelper();
        initComponent();
        if (checkOpenBuild(OPEN_EVENTBUS)) {
            EventBus.getDefault().register(this);
        }
        if (checkOpenBuild(OPEN_LOADING)) {
            mILoading = createLoadingDialog();
        }
        initUI();
        if (basePresenter != null) {
            basePresenter.onCreate();
        }
        initAnim();
        initData(savedInstanceState);

    }

    protected int initBuild() {
        return 0;
    }

    public void initUI() {

    }

    /**
     * 初始动画设置
     */
    public void initAnim() {

    }

    /**
     * dagger2注入
     */
    protected void initComponent() {

    }

    protected TipsHelper initTipsHelper() {
        DefaultTipsHelper defaultTipsHelper = null;
        View tipHelperAttachedView = findViewById(R.id.tip_helper_attached_view);
        if (tipHelperAttachedView != null) {
            defaultTipsHelper = new DefaultTipsHelper.Builder()
                    .setAttachedView(tipHelperAttachedView)
                    .setOnClickTipListener(this)
                    .setHideTargetEmpty(true)
                    .setHideTargetFailed(true)
                    .setHideTargetLoading(false)
                    .create(getApplicationContext());
        }
        return defaultTipsHelper;
    }


    /**
     * 监听重试
     *
     * @param v
     * @param status status: DefaultTipsHelper(DEFAUL_TTIPS_LOADING,DEFAUL_TTIPS_FAILED,DEFAUL_TTIP_SEMPTY)
     */
    @Override
    public void onTipClick(View v, int status) {

    }

    /**
     * @return 布局文件
     */
    public abstract int getLayoutResID();

    /**
     * @param savedInstanceState 初始数据
     */
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    /**
     * @return 全局LoadingDialog
     */
    protected ILoading createLoadingDialog() {
        LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
        loadingDialogFragment.setFragmentManager(getSupportFragmentManager());
        return loadingDialogFragment;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (checkOpenBuild(OPEN_EVENTBUS)) {
            EventBus.getDefault().unregister(this);
        }

        if (basePresenter != null) {
            basePresenter.onDestroy();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (basePresenter != null) {
            basePresenter.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (basePresenter != null) {
            basePresenter.onRestart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (basePresenter != null) {
            basePresenter.onStop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (basePresenter != null) {
            basePresenter.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (basePresenter != null) {
            basePresenter.onResume();
        }
    }

    /**
     * 显示加载
     */
    @Override
    public void showLoading() {
        if (mILoading != null) {
            mILoading.showLoading();
        }
    }


    /**
     * 隐藏加载
     */
    @Override
    public void hideLoading() {
        if (mILoading != null) {
            mILoading.hideLoading();
        }
    }


    /**
     * 显示信息
     */
    @Override
    public void showMessage(String message) {
        if (mToast != null && !TextUtils.isEmpty(message)) {
            mToast.setText(message);
            mToast.show();
        }
    }

    ;

    /**
     * 请求网络数据成功回调
     *
     * @param data
     */
    @Override
    public void onDataSuccess(T data) {

    }

    @Override
    public void onDataSuccess(String tag, T data) {

    }

    /**
     * 请求网络数据失败回调
     *
     * @param message
     */
    @Override
    public void onDataFailed(String message) {
        showMessage(message);
    }

    @Override
    public void onDataFailed(String tag, String message) {
        showMessage(message);
    }

    @Override
    public void onComplete(String tag) {

    }
    @Override
    public void onComplete() {

    }


    public void killMyself() {
        this.finish();
    }

    ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (basePresenter != null) {
            basePresenter.onActivityResult(requestCode, resultCode, data == null ? null : data.getExtras());
        }
    }

    protected MyAppComponent getApplicationComponent() {
        return ((MyApp) getApplication()).getMyAppBuild();
    }

    @Override
    public Navigator getNavigator() {
        return mNavigator;
    }

    private boolean checkOpenBuild(int build) {
        return ((build & mMainBuild) == build);
    }
}
