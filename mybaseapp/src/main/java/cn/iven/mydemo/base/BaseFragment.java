package cn.iven.mydemo.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import cn.iven.mydemo.MyApp;
import cn.iven.mydemo.Navigator;
import cn.iven.mydemo.R;
import cn.iven.mydemo.base.iface.IActivityView;
import cn.iven.mydemo.base.iface.IComponent;
import cn.iven.mydemo.base.iface.IDataProcess;
import cn.iven.mydemo.base.iface.IFragmentPresenter;
import cn.iven.mydemo.base.iface.IFragmentView;
import cn.iven.mydemo.base.iface.ILoading;
import cn.iven.mydemo.dagger.component.MyAppComponent;
import cn.iven.mydemo.module.widget.dialog.LoadingDialogFragment;
import cn.iven.mydemo.module.widget.tips.DefaultTipsHelper;
import cn.iven.mydemo.module.widget.tips.TipsHelper;


/**
 * Created by iven on 2017/6/19.
 * 1:重写initUI()对控件进行配置，例如对RecyclerView设置适配器。
 * 重写initAnim()对动画进行初始化。
 * 重写initData(savedInstanceState)对数据进行初始，例始获取网络操作
 * 此三个方法BaseFragment构建之后只执行1次
 * <p>
 * 2:跳转导航getNavigator()
 */
public abstract class BaseFragment<T> extends Fragment implements IFragmentView, IDataProcess<T>, DefaultTipsHelper.OnClickTipListener {

    private static final int OPEN_DEFUALT = 0x0001;
    public static final int OPEN_EVENTBUS = OPEN_DEFUALT << 0; //开启事件总线
    public static final int OPEN_LOADING = OPEN_DEFUALT << 1;  //开启等待框
    public static final int OPEN_EVENTBUS_LOADING = OPEN_EVENTBUS | OPEN_LOADING;//同时开启事件总线、等待框

    protected final String mTAG = this.getClass().getSimpleName();
    private MyAppComponent mMyAppBuild;
    private boolean mfirstRun = true; //界面初化时为true
    private View mInflateview;

    @Nullable
    protected IFragmentPresenter baseFragmentPresenter;
    @Nullable
    protected IActivityView mIActivityView;
    protected ILoading mILoading;
    protected TipsHelper mTipsHelper;
    private int mFragmentBuild = 0;

    @Override
    public void setBaseFragmentPresenter(@Nullable IFragmentPresenter baseFragmentPresenter) {
        this.baseFragmentPresenter = baseFragmentPresenter;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentBuild = initBuild();
        if (context instanceof IActivityView) {
            mIActivityView = (IActivityView) context;
        }
        mMyAppBuild = ((MyApp) context.getApplicationContext()).getMyAppBuild();
        initComponent();
        if (checkOpenBuild(OPEN_EVENTBUS)) {
            EventBus.getDefault().register(this);
        }
        baseFragmentPresenter = getPresenter();
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.onAttach();


    }


    /**
     * dagger2 注入
     */
    protected void initComponent() {

    }

    /**
     * @return
     */
    protected IFragmentPresenter getPresenter() {
        return baseFragmentPresenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        if (mInflateview == null) {
            mInflateview = inflater.inflate(getLayoutResID(), container, false);
            ButterKnife.bind(this, mInflateview);
            mTipsHelper = initTipsHelper();
        }
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.onCreateView(savedInstanceState);
        return mInflateview;

    }

    /**
     * @return 空界面，报错界面，等待界面设置
     */
    protected TipsHelper initTipsHelper() {
        DefaultTipsHelper defaultTipsHelper = null;
        View tipHelperAttachedView = mInflateview.findViewById(R.id.tip_helper_attached_view);
        if (mInflateview != null && tipHelperAttachedView != null) {
            defaultTipsHelper = new DefaultTipsHelper.Builder()
                    .setAttachedView(tipHelperAttachedView)
                    .setOnClickTipListener(this)
                    .setHideTargetEmpty(true)
                    .setHideTargetFailed(true)
                    .setHideTargetLoading(false)
                    .create(getActivity().getApplicationContext());
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

    @Override
    public boolean isFinishing() {
        if (mIActivityView != null) {
            return mIActivityView.isFinishing();
        }
        return mInflateview == null;
    }

    public abstract int getLayoutResID();

    protected void initData(boolean firstRun, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (checkOpenBuild(OPEN_EVENTBUS)) {
            EventBus.getDefault().unregister(this);
        }
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.onDetach();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (baseFragmentPresenter != null)
            baseFragmentPresenter.onViewCreated(savedInstanceState);


    }

    protected void initUI(boolean firstRun) {

    }


    /**
     * @return return OPEN_LOADING | OPEN_EVENTBUS
     */
    protected int initBuild() {
        return 0;
    }

    protected void initAnim(boolean firstRun) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (baseFragmentPresenter != null) {
            baseFragmentPresenter.onActivityCreated(savedInstanceState);
        }

        if (mfirstRun) {
            if (checkOpenBuild(OPEN_LOADING)) {
                mILoading = createLoadingDialog();
            }
        }
        initUI(mfirstRun);
        initAnim(mfirstRun);
        initData(mfirstRun, savedInstanceState);
        mfirstRun = false;
    }

    protected ILoading createLoadingDialog() {
        LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
        loadingDialogFragment.setFragmentManager(getChildFragmentManager());
        return loadingDialogFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (baseFragmentPresenter != null) {
            baseFragmentPresenter.onDestroyView();
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


    protected boolean checkDialogVisibility() {
        return mIActivityView != null && checkOpenBuild(OPEN_LOADING) && getUserVisibleHint();
    }


    /**
     * 显示信息
     */
    @Override
    public void showMessage(String message) {
        if (mIActivityView != null) {
            mIActivityView.showMessage(message);
        }
    }

    ;

    @Override
    public void onDataSuccess(T data) {

    }

    @Override
    public void onDataSuccess(String tag, T data) {

    }

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
        if (mIActivityView != null) {
            mIActivityView.killMyself();
        }
    }

    private boolean checkOpenBuild(int build) {
        return ((build & mFragmentBuild) == build);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (baseFragmentPresenter != null) {
            baseFragmentPresenter.onActivityResult(requestCode, resultCode,
                    data == null ? null : data.getExtras());
        }
    }

    @Override
    public Navigator getNavigator() {
        if (mIActivityView != null) {
            return mIActivityView.getNavigator();
        }
        return null;
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((IComponent<C>) getActivity()).getComponent());
    }

    protected MyAppComponent getApplicationComponent() {
        return mMyAppBuild;
    }
}
