package cn.iven.mydemo.module.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
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

public class ErrorFragment extends BaseFragment<Object> {


    public static ErrorFragment newInstance() {
        return new ErrorFragment();
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_show;
    }

    @Override
    protected void initUI(boolean firstRun) {

    }

    @Override
    protected void initData(boolean firstRun, @Nullable Bundle savedInstanceState) {
        if (firstRun) {
            setLoadView();
        }

    }

    private void setLoadView() {
        Random random = new Random();
        int i = random.nextInt(5);
        switch (i) {
            case 0:
                mTipsHelper.showLoading(true);
                break;
            case 1:
                mTipsHelper.showEmpty();
                break;
            case 2:
                mTipsHelper.showError(true, "网络连接失败");
                break;
            case 3:
                mTipsHelper.showLoading(true);
                break;
            case 4:
                mTipsHelper.showError(true, "网络连接失败");
                break;
        }
    }

    @Override
    public void onTipClick(View v, int status) {
        switch (status) {
            case DefaultTipsHelper.DEFAUL_TTIPS_FAILED:
                setLoadView();
                break;
        }
    }
}
