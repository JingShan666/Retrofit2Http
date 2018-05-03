package net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2018/5/3.
 */

public class RestCreator {

    //静态内部类创建单例模式
    /**
     * 构建Retrofit
     */
    private static final class RetrofitHolder{
        private static final String BASE_URL=BaseUrl.BASE_URL;

        private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                .baseUrl("https://www.baidu.com/")
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    /**
     * 构建OKHttpClient
     */
    private static final class OKHttpHolder{
        private static final int TIME_OUT=60;

        private static final OkHttpClient OK_HTTP_CLIENT= new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


    /**
     * 构建RestService
     */

    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE=
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    /**
     * 获取RestService实例
     */

    public static RestService getRestService(){

        return RestServiceHolder.REST_SERVICE;

    }


}
