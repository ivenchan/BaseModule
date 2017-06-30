package cn.iven.mydemo.presenter;

import javax.inject.Inject;

import cn.iven.mydemo.base.BaseFragment;
import cn.iven.mydemo.base.BaseFragmentPresenter;
import cn.iven.mydemo.model.api.GanService;
import cn.iven.mydemo.model.api.LearnService;
import cn.iven.mydemo.model.bean.GankListBean;
import io.reactivex.Observable;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chenzuoying on 2017/5/26.
 */

public class PicFragmentPresenter extends BaseFragmentPresenter {

    @Inject
    public GanService mGanService;

    @Inject
    public PicFragmentPresenter(BaseFragment baseFragment) {
        super(baseFragment);
    }

    public void groupList(String tag, int page, int num) {
        getData(tag, mGanService.groupList(page, num));
    }

    public void groupList(int page, int num) {
        getData(mGanService.groupList(page, num));
    }


}
