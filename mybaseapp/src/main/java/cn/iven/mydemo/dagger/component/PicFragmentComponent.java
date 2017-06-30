package cn.iven.mydemo.dagger.component;

import dagger.Component;
import cn.iven.mydemo.dagger.base.BaseFragmentComponent;
import cn.iven.mydemo.dagger.module.PicFragmentModule;
import cn.iven.mydemo.dagger.scope.PerFragment;
import cn.iven.mydemo.module.ui.PicFragment;

/**
 * Created by iven on 2017/6/19.
 */
@PerFragment
@Component(dependencies = { MainActivityComponent.class}, modules = PicFragmentModule.class)
public interface PicFragmentComponent extends BaseFragmentComponent {
    void inject(PicFragment picFragment);
}

