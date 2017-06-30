package cn.iven.mydemo.base.iface;

/**
 * Created by iven on 2017/6/26.
 */

public interface IDataProcess<T> {


    void onDataSuccess(T data);

    void onDataSuccess(String tag, T data);

    void onDataFailed(String message);

    void onDataFailed(String tag, String message);

    void onComplete();

    void onComplete(String tag);
}
