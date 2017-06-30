package cn.iven.mydemo.base;

import android.os.Bundle;
import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;

import cn.iven.mydemo.base.iface.IActivityPresenter;
import cn.iven.mydemo.base.iface.IActivityView;
import cn.iven.mydemo.base.iface.IDataProcess;
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

public class BaseActivityPresenter implements IActivityPresenter, IRxProcess, Observer {

    private static final int OPEN_DEFUALT = 0x0001;
    public static final int OPEN_EVENTBUS = OPEN_DEFUALT << 0; //开启事件总线
    public static final int OPEN_SINGLETON_DISPOSABLE = OPEN_DEFUALT << 1; //开启请求单例模式
    public static final int OPEN_EVENTBUS_SINGLETON = OPEN_EVENTBUS | OPEN_SINGLETON_DISPOSABLE;

    protected CompositeDisposable mCompositeDisposable;
    protected Disposable mDisposable;
    protected IActivityView mIActivityView;
    protected IDataProcess mIDataProcess;

    private int mPresenterBuild = 0;

    public BaseActivityPresenter(BaseActivity baseActivity) {
        mPresenterBuild = initBuild();
        if (!checkOpenBuild(OPEN_SINGLETON_DISPOSABLE)) {
            mCompositeDisposable = new CompositeDisposable();
        }
        this.mIActivityView = baseActivity;
        mIDataProcess = baseActivity;
        this.mIActivityView.setBasePresenter(this);
    }

    /**
     * ConstBuild.Presenter.OPEN_EVENTBUS
     * ConstBuild.Presenter.OPEN_LOADING
     * ConstBuild.Presenter.OPEN_SINGLETON_DISPOSABLE
     * <p>
     * protected int initBuild() {
     * return ConstBuild.Presenter.OPEN_SINGLETON_DISPOSABLE|ConstBuild.Presenter.OPEN_LOADING;
     * }
     *
     * @return
     */
    protected int initBuild() {
        return 0;
    }

    public <T> void getData(Observable<T> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }


    public <T> void getData(String tag, Observable<T> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new TagObserver(tag, this));
    }

    @Override
    public void onCreate() {
        if (checkOpenBuild(OPEN_EVENTBUS)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mIActivityView = null;
        mIDataProcess = null;
        if (mCompositeDisposable != null && mCompositeDisposable.size() > 0) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        if (checkOpenBuild(OPEN_EVENTBUS)) {
            EventBus.getDefault().unregister(this);
        }
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
        if (mIActivityView != null) {
            mIActivityView.showLoading();
        }
        if (!checkOpenBuild(OPEN_SINGLETON_DISPOSABLE)) {
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
        if (mIActivityView == null || mIActivityView.isFinishing()) {
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
                // Error
            } else {
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
        if (mIActivityView == null || mIActivityView.isFinishing()) {
            return;
        }
        mIActivityView.hideLoading();
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
        if (mIActivityView == null || mIActivityView.isFinishing()) {
            return;
        }
        if (tag == null) {
            mIDataProcess.onComplete();
        } else {
            mIDataProcess.onComplete(tag);
        }
    }

    private boolean checkOpenBuild(int build) {
        return ((build & mPresenterBuild) == build);
    }
}
