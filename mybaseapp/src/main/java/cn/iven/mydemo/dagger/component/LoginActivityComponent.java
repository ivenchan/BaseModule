package cn.iven.mydemo.dagger.component;

import dagger.Component;
import cn.iven.mydemo.dagger.base.BaseActivityComponent;
import cn.iven.mydemo.dagger.module.LoginActivityModule;
import cn.iven.mydemo.dagger.scope.PerActivity;
import cn.iven.mydemo.module.ui.LoginActivity;

/**
 * Created by iven on 2017/6/19.
 */
@PerActivity
@Component(dependencies = {MyAppComponent.class}, modules = LoginActivityModule.class)
public interface LoginActivityComponent extends BaseActivityComponent {
    void inject(LoginActivity activity);
}
