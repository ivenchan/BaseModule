package cn.iven.mydemo.base.iface;

import cn.iven.mydemo.Navigator;

/**
 * Created by iven on 2017/6/19.
 */

public interface IFragmentView {
    boolean isFinishing();

    void setBaseFragmentPresenter(IFragmentPresenter baseFragmentPresenter);

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);
    /**
     * 杀死自己
     */
    void killMyself();

    Navigator getNavigator();
}
