package cn.iven.mydemo.dagger.module;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by iven on 2017/6/19.
 */
@Module
public class MyAppModule {

    Context mContext;

    public MyAppModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    public Toast provideToast() {
        return Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
    }

}
