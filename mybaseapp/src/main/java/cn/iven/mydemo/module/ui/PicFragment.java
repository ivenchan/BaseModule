package cn.iven.mydemo.module.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iven.mydemo.R;
import cn.iven.mydemo.base.BaseFragment;
import cn.iven.mydemo.dagger.component.DaggerPicFragmentComponent;
import cn.iven.mydemo.dagger.component.MainActivityComponent;
import cn.iven.mydemo.dagger.module.PicFragmentModule;
import cn.iven.mydemo.model.bean.GankListBean;
import cn.iven.mydemo.model.bean.IWantToLearnBean;
import cn.iven.mydemo.module.adapter.PicAdapter;
import cn.iven.mydemo.module.widget.tips.DefaultTipsHelper;
import cn.iven.mydemo.module.widget.tips.TipsHelper;
import cn.iven.mydemo.presenter.PicFragmentPresenter;

/**
 * Created by iven on 2017/6/19.
 */

public class PicFragment extends BaseFragment<Object> {


    public static PicFragment newInstance() {
        return new PicFragment();
    }

    @BindView(R.id.rylist)
    RecyclerView mRylist;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_pic_show;
    }

    @Inject
    PicFragmentPresenter mPicFragmentPresenter;

    @Inject
    PicAdapter mPicAdapter;

    @Override
    protected int initBuild() {
        return OPEN_EVENTBUS;
    }

    @Override
    protected void initUI(boolean firstRun) {
        if (firstRun) {
            mRylist.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            mRylist.setAdapter(mPicAdapter);
        }
    }

    @Override
    protected TipsHelper initTipsHelper() {
        return new DefaultTipsHelper.Builder()
                .setAttachedView(mRylist)
                .setOnClickTipListener(new DefaultTipsHelper.OnClickTipListener() {
                    @Override
                    public void onTipClick(View v, int status) {
                        mTipsHelper.hideError();
                    }
                })
                .setHideTargetEmpty(false)
                .setHideTargetFailed(false)
                .setHideTargetLoading(false)
                .create(getContext());
    }

    @Override
    protected void initData(boolean firstRun, @Nullable Bundle savedInstanceState) {
        if (firstRun) {
            //没有tag请求走onDataSuccess(Object data)
            mPicFragmentPresenter.groupList(1, 100);

            //设置tag成功走onDataSuccess(String tag, Object data),可以区分一个界面多个请求处理
//            mPicFragmentPresenter.groupList(GankListBean.class.getSimpleName(), 1, 100);

            mTipsHelper.showLoading(true);
        }

    }

    @Override
    protected void initComponent() {
        DaggerPicFragmentComponent.builder()
                .mainActivityComponent(getComponent(MainActivityComponent.class))
                .picFragmentModule(new PicFragmentModule(this))
                .build().inject(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        showMessage(event);
    }

    ;

    @Override
    public void onDataSuccess(Object data) {
        mTipsHelper.hideLoading();
        mPicAdapter.clearDatas();
        if (data instanceof GankListBean) {
            GankListBean gankListBean = (GankListBean) data;
            mPicAdapter.setDatas(gankListBean.getResults());
        } else if (data instanceof IWantToLearnBean) {
        }
    }

    @Override
    public void onDataSuccess(String tag, Object data) {
        mTipsHelper.hideLoading();
        switch (tag) {
            case "GankListBean":
                mPicAdapter.clearDatas();
                if (data instanceof GankListBean) {
                    GankListBean gankListBean = (GankListBean) data;
                    mPicAdapter.setDatas(gankListBean.getResults());
                } else if (data instanceof IWantToLearnBean) {
                }
                break;
        }

    }

    @Override
    public void onDataFailed(String message) {
        super.onDataFailed(message);
    }

}
