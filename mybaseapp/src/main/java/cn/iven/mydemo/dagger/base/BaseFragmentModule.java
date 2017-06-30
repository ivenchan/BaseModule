package cn.iven.mydemo.dagger.base;

import cn.iven.mydemo.base.BaseFragment;
import cn.iven.mydemo.dagger.scope.PerFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by iven on 2017/6/19.
 */
@Module
public class BaseFragmentModule {
    private BaseFragment mBaseFragment;

    public BaseFragmentModule(BaseFragment baseFragment) {
        mBaseFragment = baseFragment;

    }

    @PerFragment
    @Provides
    public BaseFragment provideBaseFragment() {
        return mBaseFragment;
    }

}
