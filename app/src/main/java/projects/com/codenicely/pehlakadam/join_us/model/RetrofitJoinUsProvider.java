package projects.com.codenicely.pehlakadam.join_us.model;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.join_us.JoinUsCallBack;
import projects.com.codenicely.pehlakadam.join_us.api.JoinUsApi;
import projects.com.codenicely.pehlakadam.join_us.model.data.JoinUsData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aman on 20/6/17.
 */

public class RetrofitJoinUsProvider implements JoinUsProvider {
    private JoinUsApi joinUsApi;
    private Context context;
    private Call<JoinUsData> call;

    public RetrofitJoinUsProvider(Context context) {

        this.context=context;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        joinUsApi = retrofit.create(JoinUsApi.class);

    }

    @Override
    public void requestJoinUs(String access_token,String desc , final JoinUsCallBack joinUsCallBack) {
        call = joinUsApi.requestJoinUs(access_token,desc);
        call.enqueue(new Callback<JoinUsData>() {
            @Override
            public void onResponse(Call<JoinUsData> call, Response<JoinUsData> response) {
                joinUsCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<JoinUsData> call, Throwable t) {
                t.printStackTrace();
                joinUsCallBack.onFailure();
            }
        });
    }

    @Override
    public void onDestroy() {
        if (call!= null){
            call.cancel();
        }
    }


}
