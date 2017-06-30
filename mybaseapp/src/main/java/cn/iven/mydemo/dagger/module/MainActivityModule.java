package cn.iven.mydemo.dagger.module;

import android.support.v4.app.FragmentManager;

import cn.iven.mydemo.model.api.LearnService;
import dagger.Module;
import dagger.Provides;
import cn.iven.mydemo.MainActivity;
import cn.iven.mydemo.dagger.base.BaseActivityModule;
import cn.iven.mydemo.dagger.scope.PerActivity;
import cn.iven.mydemo.model.api.GanService;
import retrofit2.Retrofit;

/**
 * Created by iven on 2017/6/19.
 */
@Module
public class MainActivityModule extends BaseActivityModule {
    private MainActivity mActivity;

    public MainActivityModule(MainActivity activity) {
        super(activity);
        mActivity = activity;
    }

    @PerActivity
    @Provides
    public GanService provideGanService(Retrofit retrofit) {
        return retrofit.create(GanService.class);
    }

    @PerActivity
    @Provides
    public LearnService provideLearnService(Retrofit retrofit) {
        return retrofit.create(LearnService.class);
    }

    @PerActivity
    @Provides
    public FragmentManager providePicPageAdapter() {
        return mActivity.getSupportFragmentManager();
    }


}
