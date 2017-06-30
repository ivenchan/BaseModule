package cn.iven.mydemo.dagger.module;

import cn.iven.mydemo.base.iface.IActivityView;
import cn.iven.mydemo.presenter.LoginActivityPresenter;
import cn.iven.mydemo.presenter.iface.ILoginActivityPresenter;
import dagger.Module;
import dagger.Provides;
import cn.iven.mydemo.dagger.base.BaseActivityModule;
import cn.iven.mydemo.model.api.LoginService;
import cn.iven.mydemo.module.ui.LoginActivity;
import retrofit2.Retrofit;

/**
 * Created by iven on 2017/6/19.
 */
@Module
public class LoginActivityModule extends BaseActivityModule {

    public LoginActivityModule(LoginActivity activity) {
        super(activity);
    }


    @Provides
    public LoginService provideGanService(Retrofit retrofit) {
        return retrofit.create(LoginService.class);
    }

//    @Provides
//    public ILoginActivityPresenter getILoginActivityPresenter(IActivityView view,
//                                                              LoginService loginService) {
//        return new LoginActivityPresenter(view).
//                setLoginService(loginService);
//    }

}
