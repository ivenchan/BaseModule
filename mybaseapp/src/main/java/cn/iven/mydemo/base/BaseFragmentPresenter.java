package cn.iven.mydemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;

import cn.iven.mydemo.base.iface.IDataProcess;
import cn.iven.mydemo.base.iface.IFragmentPresenter;
import cn.iven.mydemo.base.iface.IFragmentView;
import cn.iven.mydemo.base.iface.IRxProcess;
import cn.iven.mydemo.model.bean.BaseResponse;
import cn.iven.mydemo.utils.ExceptionUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by iven on 2017/6/19.
 */

public class BaseFragmentPresenter implements IFragmentPresenter, IRxProcess, Observer {

    private static final int OPEN_DEFUALT = 0x0001;
    public static final int OPEN_EVENTBUS = OPEN_DEFUALT << 0; //开启事件总线
    public static final int OPEN_SINGLETON_DISPOSABLE = OPEN_DEFUALT << 1; //开启请求单例模式
    public static final int OPEN = OPEN_EVENTBUS | OPEN_SINGLETON_DISPOSABLE;

    protected CompositeDisposable mCompositeDisposable;
    protected Disposable mDisposable;
    protected IFragmentView mIFragmentView;
    protected IDataProcess mIDataProcess;
    private boolean mIsVisibleToUser;

    private int mPresenterBuild = 0;

    public BaseFragmentPresenter(BaseFragment baseFragment) {
        mPresenterBuild = initBuild();
        if (!checkOpenBuild(OPEN_SINGLETON_DISPOSABLE)) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mIDataProcess = baseFragment;
        mIFragmentView = baseFragment;
        mIFragmentView.setBaseFragmentPresenter(this);
    }

    /**
     * @return
     */
    protected int initBuild() {
        return 0;
    }

    public <T> void getData(Observable<T> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this);
    }

    public <T> void getData(String tag, Observable<T> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new TagObserver(tag, this));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        mIsVisibleToUser = isVisibleToUser;
    }

    @Override
    public void onAttach() {
        if (checkOpenBuild(OPEN_EVENTBUS)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mIFragmentView = null;
        mIDataProcess = null;
        if (mCompositeDisposable != null && mCompositeDisposable.size() > 0) {
            mCompositeDisposable.dispose();
        }
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDetach() {
        if (checkOpenBuild(OPEN_EVENTBUS)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onViewCreated(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Bundle data) {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        addDisposable(d);
    }

    @Override
    public void addDisposable(Disposable d) {
        if (mIsVisibleToUser && mIFragmentView != null) {
            mIFragmentView.showLoading();
        }
        if (mCompositeDisposable != null) {
            mCompositeDisposable.add(d);
        } else {
            if (mDisposable != null) {
                mDisposable.dispose();
            }
            mDisposable = d;
        }
    }

    @Override
    public void onNext(@NonNull Object o) {
        onNext(null, o);
    }

    @Override
    public void onNext(String tag, Object o) {
        if (mIFragmentView == null || mIFragmentView.isFinishing()) {
            return;
        }
        if (o instanceof BaseResponse) {
            //统一数据处理
            BaseResponse tmp = (BaseResponse) o;
            if (TextUtils.equals(tmp.getError(), "0")) {
                if (tag == null) {
                    if (mIDataProcess != null)
                        mIDataProcess.onDataSuccess(tmp.getData());
                } else {
                    if (mIDataProcess != null)
                        mIDataProcess.onDataSuccess(tag, tmp.getData());
                }
            } else {
                // Error
                if (tag == null) {
                    if (mIDataProcess != null)
                        mIDataProcess.onDataFailed(tmp.getMessage());
                } else {
                    if (mIDataProcess != null)
                        mIDataProcess.onDataFailed(tag, tmp.getMessage());
                }
            }
        } else {
            //自定义消息处理
            if (tag == null) {
                if (mIDataProcess != null)
                    mIDataProcess.onDataSuccess(o);
            } else {
                if (mIDataProcess != null)
                    mIDataProcess.onDataSuccess(tag, o);
            }
        }
    }


    @Override
    public void onError(@NonNull Throwable e) {
        onError(null, e);
    }

    @Override
    public void onError(String tag, @NonNull Throwable e) {
        if (mIFragmentView == null || mIFragmentView.isFinishing()) {
            return;
        }
        mIFragmentView.hideLoading();
        if (tag == null) {
            if (mIDataProcess != null)
                mIDataProcess.onDataFailed(ExceptionUtils.handleException(e));
        } else {
            if (mIDataProcess != null)
                mIDataProcess.onDataFailed(tag, ExceptionUtils.handleException(e));
        }
    }

    @Override
    public void onComplete() {
        onComplete(null);
    }

    @Override
    public void onComplete(String tag) {
        if (mIFragmentView == null || mIFragmentView.isFinishing()) {
            return;
        }
        if (tag == null) {
            if (mIDataProcess != null)
                mIDataProcess.onComplete();
        } else {
            if (mIDataProcess != null)
                mIDataProcess.onComplete(tag);
        }
    }

    private boolean checkOpenBuild(int build) {
        return ((build & mPresenterBuild) == build);
    }
}
