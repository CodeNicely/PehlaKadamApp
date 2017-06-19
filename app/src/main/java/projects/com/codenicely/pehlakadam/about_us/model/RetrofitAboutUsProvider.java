package projects.com.codenicely.pehlakadam.about_us.model;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.about_us.AboutUsCallBack;
import projects.com.codenicely.pehlakadam.about_us.api.AboutUsApi;
import projects.com.codenicely.pehlakadam.about_us.data.AboutUsData;
import projects.com.codenicely.pehlakadam.helper.Urls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meghal on 13/10/16.
 */

public class RetrofitAboutUsProvider implements AboutUsProvider {

    private AboutUsApi aboutUsApi;
    private Call<AboutUsData> aboutUsDataCall;

    public RetrofitAboutUsProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
//                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR).cache(RetrofitCache.provideCache())
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        aboutUsApi = retrofit.create(AboutUsApi.class);

    }


    @Override
    public void requestAboutUs(int lang_type,final AboutUsCallBack aboutUsCallBack) {

        aboutUsDataCall = aboutUsApi.getAboutUsData(lang_type);

        aboutUsDataCall.enqueue(new Callback<AboutUsData>() {
            @Override
            public void onResponse(Call<AboutUsData> call, Response<AboutUsData> response) {

                aboutUsCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AboutUsData> call, Throwable t) {

                aboutUsCallBack.onFailure();
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onDestroy() {
        aboutUsDataCall.cancel();
    }
}
