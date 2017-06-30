package cn.iven.mydemo.dagger.base;

import cn.iven.mydemo.base.BaseActivity;
import cn.iven.mydemo.dagger.component.MyAppComponent;
import cn.iven.mydemo.dagger.scope.PerActivity;
import dagger.Component;

/**
 * Created by iven on 2017/6/19.
 */
@PerActivity
@Component(dependencies = {MyAppComponent.class}, modules = BaseActivityModule.class)
public interface BaseActivityComponent {
    BaseActivity getIActivityView();
}
