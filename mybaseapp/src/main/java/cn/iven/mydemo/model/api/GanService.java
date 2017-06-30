package cn.iven.mydemo.model.api;

import io.reactivex.Observable;
import cn.iven.mydemo.constant.ConstUrl;
import cn.iven.mydemo.model.bean.GankListBean;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by iven on 2017/6/19.
 */

public interface GanService {
    @GET(ConstUrl.GAN_LIST_URL)
    Observable<GankListBean> groupList(@Path("page") int page,@Path("num") int num);
}
