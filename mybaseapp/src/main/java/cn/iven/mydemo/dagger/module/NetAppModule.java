package cn.iven.mydemo.dagger.module;

import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import cn.iven.mydemo.factory.RetrofitFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by iven on 2017/6/19.
 * 网络连接设置
 */
@Module
public class NetAppModule {
    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder retrofitBuilder = RetrofitFactory.getRetrofitBuilder();
        return retrofitBuilder.client(okHttpClient).build();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(List<Interceptor> interceptorList, ClearableCookieJar cookieJar) {
        OkHttpClient.Builder httpClientBuilder = RetrofitFactory.getOkHttpClientBuilder();
        if (cookieJar != null) {
            httpClientBuilder.cookieJar(cookieJar);
        }
        for (Interceptor interceptor : interceptorList) {
            if (interceptor != null)
                httpClientBuilder.addInterceptor(interceptor);
        }
        return httpClientBuilder.build();
    }

    @Singleton
    @Provides
    public List<Interceptor> provideOkHttpInterceptor() {
        List<Interceptor> list = new ArrayList<>();
        list.add(RetrofitFactory.getInterceptorHeader());
        list.add(RetrofitFactory.getLoggingInterceptor());
        return list;
    }

    @Singleton
    @Provides
    public ClearableCookieJar provideCookieJar(Context context) {
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        return cookieJar;
    }

}
