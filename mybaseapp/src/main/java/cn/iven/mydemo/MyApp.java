package cn.iven.mydemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.zhy.autolayout.config.AutoLayoutConifg;

import cn.iven.mydemo.dagger.component.DaggerMyAppComponent;
import cn.iven.mydemo.dagger.component.MyAppComponent;
import cn.iven.mydemo.dagger.module.MyAppModule;
import cn.iven.mydemo.dagger.module.NetAppModule;

/**
 * Created by iven on 2017/6/19.
 */

public class MyApp extends Application {

    public MyAppComponent getMyAppBuild() {
        return mMyAppBuild;
    }

    public MyAppComponent mMyAppBuild;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        mMyAppBuild = DaggerMyAppComponent.builder()
                .myAppModule(new MyAppModule(this))
                .netAppModule(new NetAppModule()).build();

        AutoLayoutConifg.getInstance().useDeviceSize();


    }
}
