package cn.iven.mydemo.presenter;

import android.os.SystemClock;

import javax.inject.Inject;

import cn.iven.mydemo.base.BaseActivity;
import cn.iven.mydemo.base.BaseActivityPresenter;
import cn.iven.mydemo.dagger.scope.PerActivity;
import cn.iven.mydemo.model.api.LoginService;
import cn.iven.mydemo.presenter.iface.ILoginActivityPresenter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by iven on 2017/6/19.
 */
@PerActivity
public class LoginActivityPresenter extends BaseActivityPresenter implements ILoginActivityPresenter {
    @Inject
    public LoginActivityPresenter setLoginService(LoginService loginService) {
        mLoginService = loginService;
        return this;
    }

    @Inject
    public LoginService mLoginService;

    @Inject
    public LoginActivityPresenter(BaseActivity view) {
        super(view);
    }


    @Override
    public void login(String account, String pwd) {

        getData(Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                SystemClock.sleep(1000);
                e.onNext("登陆成功");
                e.onComplete();
            }
        }));
//        getData(mLoginService.login(account, pwd));
    }

    public void show() {
        mIActivityView.showMessage("测试显示！");
    }

}
