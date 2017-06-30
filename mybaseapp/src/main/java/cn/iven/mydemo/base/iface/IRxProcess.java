package cn.iven.mydemo.base.iface;

import io.reactivex.disposables.Disposable;

/**
 * Created by iven on 2017/6/26.
 */

public interface IRxProcess {
    void onError(String tag,  Throwable e);
    void onComplete(String tag);
    void onNext(String tag, Object o);
    void addDisposable(Disposable d);
}
