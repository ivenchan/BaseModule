package cn.iven.mydemo.module.ui;

import android.util.Log;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iven.mydemo.R;
import cn.iven.mydemo.base.BaseActivity;
import cn.iven.mydemo.dagger.component.DaggerLoginActivityComponent;
import cn.iven.mydemo.dagger.module.LoginActivityModule;
import cn.iven.mydemo.model.bean.LoginResponse;
import cn.iven.mydemo.presenter.LoginActivityPresenter;
import cn.iven.mydemo.presenter.iface.ILoginActivityPresenter;

/**
 * Created by iven on 2017/6/19.
 */

public class LoginActivity extends BaseActivity<String> {

    @Inject
    LoginActivityPresenter mILoginActivityPresenter;

    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;


    @Override
    public int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected int initBuild() {
        return OPEN_LOADING;
    }

    @Override
    protected void initComponent() {
        DaggerLoginActivityComponent.builder().myAppComponent(getApplicationComponent())
                .loginActivityModule(new LoginActivityModule(this)).build().inject(this);
    }

    @Override
    public void initUI() {
        if (mILoading != null) {
            mILoading.setTipDesc("登陆中...");
        }
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        mILoginActivityPresenter.login(
                mEtAccount.getText().toString().trim(),
                mEtPwd.getText().toString());
    }

    @Override
    public void onDataSuccess(String data) {
        mILoading.hideLoading();
        mNavigator.navigateToMainActivity(this);
    }

}
