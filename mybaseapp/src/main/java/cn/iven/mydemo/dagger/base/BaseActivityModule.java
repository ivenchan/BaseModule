package cn.iven.mydemo.dagger.base;

import cn.iven.mydemo.base.BaseActivity;
import cn.iven.mydemo.dagger.scope.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by iven on 2017/6/19.
 */
@Module
public class BaseActivityModule {
    protected BaseActivity mBaseActivity;


    public BaseActivityModule(BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
    }

    @PerActivity
    @Provides
    public BaseActivity provideIActivityView() {
        return mBaseActivity;
    }


}
