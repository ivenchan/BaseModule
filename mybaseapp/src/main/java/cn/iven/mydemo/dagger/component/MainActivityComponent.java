package cn.iven.mydemo.dagger.component;

import cn.iven.mydemo.model.api.LearnService;
import dagger.Component;
import cn.iven.mydemo.MainActivity;
import cn.iven.mydemo.dagger.base.BaseActivityComponent;
import cn.iven.mydemo.dagger.module.MainActivityModule;
import cn.iven.mydemo.dagger.scope.PerActivity;
import cn.iven.mydemo.model.api.GanService;

/**
 * Created by iven on 2017/6/19.
 */
@PerActivity
@Component(dependencies = {MyAppComponent.class}, modules = MainActivityModule.class)
public interface MainActivityComponent extends BaseActivityComponent {
    void inject(MainActivity activity);
    GanService getGanService();
    LearnService getLearnService();
}
