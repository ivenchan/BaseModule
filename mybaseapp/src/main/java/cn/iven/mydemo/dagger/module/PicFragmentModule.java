package cn.iven.mydemo.dagger.module;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import cn.iven.mydemo.dagger.base.BaseFragmentModule;
import cn.iven.mydemo.dagger.scope.PerFragment;
import cn.iven.mydemo.module.ui.PicFragment;

/**
 * Created by iven on 2017/6/19.
 */
@Module
public class PicFragmentModule extends BaseFragmentModule {
    Fragment mFragment;

    public PicFragmentModule(PicFragment view) {
        super(view);
        mFragment = view;
    }
    @PerFragment
    @Provides
    public Fragment provideFragment(){
        return mFragment;
    }
}
