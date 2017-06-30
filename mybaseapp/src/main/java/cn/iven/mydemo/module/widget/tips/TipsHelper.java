package cn.iven.mydemo.module.widget.tips;


public interface TipsHelper {

    void showEmpty();

    void hideEmpty();

    void showLoading(boolean firstPage);

    void hideLoading();

    void showError(boolean firstPage, String errorMessage);
    void hideError();


}