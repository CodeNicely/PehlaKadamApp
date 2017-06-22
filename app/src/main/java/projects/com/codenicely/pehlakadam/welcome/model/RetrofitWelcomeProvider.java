package projects.com.codenicely.pehlakadam.welcome.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.welcome.LoginCallback;
import projects.com.codenicely.pehlakadam.welcome.WelcomeCallBack;
import projects.com.codenicely.pehlakadam.login.api.LoginApi;
import projects.com.codenicely.pehlakadam.welcome.api.WelcomeApi;
import projects.com.codenicely.pehlakadam.login.data.LoginResponse;
import projects.com.codenicely.pehlakadam.welcome.data.WelcomeData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aman on 4/2/17  .
 */

public class RetrofitWelcomeProvider implements WelcomeProvider {

    private WelcomeApi welcomeApi;
    private LoginApi loginApi;
    private Retrofit retrofit;

    public RetrofitWelcomeProvider()
    {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson=new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

    }

    @Override
    public void requestWelcomeData(int lang_type,final WelcomeCallBack welcomeCallBack) {
        welcomeApi = retrofit.create(WelcomeApi.class);
        Call<WelcomeData> call = welcomeApi.requestWelcomeData(lang_type);
        call.enqueue(new Callback<WelcomeData>() {
            @Override
            public void onResponse(Call<WelcomeData> call, Response<WelcomeData> response) {
                welcomeCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<WelcomeData> call, Throwable t) {
                t.printStackTrace();
                welcomeCallBack.onFailure();
            }
        });
    }

    @Override
    public void requestLogin(String mobile, final LoginCallback loginCallback) {
        loginApi=retrofit.create(LoginApi.class);
        Call<LoginResponse> loginResponseCall = loginApi.requestLogin(mobile);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                loginCallback.onFailure();
            }
        });

    }
}
