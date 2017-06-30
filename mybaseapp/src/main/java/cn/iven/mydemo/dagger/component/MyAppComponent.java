package cn.iven.mydemo.dagger.component;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Singleton;

import dagger.Component;
import cn.iven.mydemo.Navigator;
import cn.iven.mydemo.dagger.module.MyAppModule;
import cn.iven.mydemo.dagger.module.NetAppModule;
import retrofit2.Retrofit;

/**
 * Created by iven on 2017/6/19.
 */
@Singleton
@Component(modules = {MyAppModule.class, NetAppModule.class})
public interface MyAppComponent {

    Context getContext();

    Toast getToast();

    Retrofit getRetrofit();

    Navigator getNavigator();

}
