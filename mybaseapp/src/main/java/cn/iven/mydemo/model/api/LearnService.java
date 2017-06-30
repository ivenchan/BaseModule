package cn.iven.mydemo.model.api;

import cn.iven.mydemo.constant.ConstUrl;
import cn.iven.mydemo.model.bean.BaseResponse;
import cn.iven.mydemo.model.bean.IWantToLearnBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenzuoying on 2017/6/22.
 */

public interface LearnService {
    @GET(ConstUrl.GET_TO_LEARN)
    Observable<BaseResponse<IWantToLearnBean>> getIWantToLearn(@Query("per-page") int perpage, @Query("page") int page);
}
