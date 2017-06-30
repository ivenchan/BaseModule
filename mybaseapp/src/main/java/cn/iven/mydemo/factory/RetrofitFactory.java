package cn.iven.mydemo.factory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.iven.mydemo.constant.ConstUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iven on 2017/6/19.
 */

public class RetrofitFactory {


    private static final long TIMEOUT = 30;

    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    public static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    public static OkHttpClient.Builder getOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS);
        return builder;
    }

    /*
    这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
    出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
    */
    public static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    // 添加通用的Header
    public static Interceptor getInterceptorHeader() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("token", "123");
                return chain.proceed(builder.build());
            }
        };

    }

    // 添加通用的Header
    public static Retrofit.Builder getRetrofitBuilder() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstUrl.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder;
    }


}
