package cn.iven.mydemo;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import javax.inject.Inject;

import butterknife.BindView;
import cn.iven.mydemo.base.BaseActivity;
import cn.iven.mydemo.base.iface.IComponent;
import cn.iven.mydemo.dagger.component.DaggerMainActivityComponent;
import cn.iven.mydemo.dagger.component.MainActivityComponent;
import cn.iven.mydemo.dagger.module.MainActivityModule;
import cn.iven.mydemo.module.adapter.PicPageAdapter;

public class MainActivity extends BaseActivity implements IComponent<MainActivityComponent> {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;

    @Inject
    PicPageAdapter mPicPageAdapter;

    private MainActivityComponent mMainActivityComponent;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {
        mVp.setAdapter(mPicPageAdapter);
        mTab.setupWithViewPager(mVp);
    }

    @Override
    protected void initComponent() {
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                .myAppComponent(getApplicationComponent())
                .mainActivityModule(new MainActivityModule(this)).build();
        mMainActivityComponent.inject(this);
    }

    @Override
    public MainActivityComponent getComponent() {
        return mMainActivityComponent;
    }

    public static Intent getMainActivityIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}
