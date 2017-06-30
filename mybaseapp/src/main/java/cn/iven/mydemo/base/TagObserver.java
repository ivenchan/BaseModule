package cn.iven.mydemo.base;

import cn.iven.mydemo.base.iface.IRxProcess;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by iven on 2017/6/26.
 */

public class TagObserver implements Observer {
    private String mTag;
    private IRxProcess mIRxProcess;

    public TagObserver(String tag, IRxProcess IRxProcess) {
        mTag = tag;
        mIRxProcess = IRxProcess;
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mIRxProcess.addDisposable(d);
    }

    @Override
    public void onNext(@NonNull Object o) {
        mIRxProcess.onNext(mTag, o);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        mIRxProcess.onError(mTag, e);
    }

    @Override
    public void onComplete() {
        mIRxProcess.onComplete(mTag);
    }
}
