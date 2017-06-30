package cn.iven.mydemo.model.api;

import io.reactivex.Observable;
import cn.iven.mydemo.constant.ConstUrl;
import cn.iven.mydemo.model.bean.BaseResponse;
import cn.iven.mydemo.model.bean.LoginResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by iven on 2017/6/19.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST(ConstUrl.LOGIN)
    Observable<BaseResponse<LoginResponse>> login(@Field("account") String account,
                                                  @Field("password") String password);
}
