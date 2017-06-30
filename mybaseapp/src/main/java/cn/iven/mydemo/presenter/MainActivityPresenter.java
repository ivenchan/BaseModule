package cn.iven.mydemo.presenter;

import javax.inject.Inject;

import cn.iven.mydemo.base.BaseActivity;
import cn.iven.mydemo.base.BaseActivityPresenter;
import cn.iven.mydemo.dagger.scope.PerActivity;
import cn.iven.mydemo.model.api.GanService;

/**
 * Created by iven on 2017/6/19.
 */
@PerActivity
public class MainActivityPresenter extends BaseActivityPresenter{

    @Inject
    public GanService mGanService;

    @Inject
    public void setGanService() {

    }

    @Inject
    public MainActivityPresenter(BaseActivity view) {
        super(view);
    }

    public void show() {
        mIActivityView.showMessage("测试显示！");
    }

}
