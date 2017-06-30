package cn.iven.mydemo.base.iface;

import android.os.Bundle;

/**
 * Created by iven on 2017/6/19.
 */

public interface IActivityPresenter {
    void onCreate();
    void onStart();
    void onRestart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void onActivityResult(int requestCode,int resultCode,Bundle data);
}
