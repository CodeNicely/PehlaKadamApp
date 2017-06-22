package projects.com.codenicely.pehlakadam.login.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.login.PreLoginCallback;
import projects.com.codenicely.pehlakadam.login.api.LoginApi;
import projects.com.codenicely.pehlakadam.login.api.PreLoginApi;
import projects.com.codenicely.pehlakadam.login.data.LoginResponse;
import projects.com.codenicely.pehlakadam.login.data.PreLoginData;
import projects.com.codenicely.pehlakadam.welcome.LoginCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ujjwal on 21/6/17.
 */


public class RetrofitLoginHelper implements LoginHelper {
	Retrofit retrofit;
	LoginApi loginApi;
	PreLoginApi preLoginApi;

	public RetrofitLoginHelper() {

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

	@Override
	public void requestPreLoginData(final PreLoginCallback preLoginCallback) {
		preLoginApi = retrofit.create(PreLoginApi.class);
		Call<PreLoginData> preLoginDataCall = preLoginApi.requestPreLoginData();
		preLoginDataCall.enqueue(new Callback<PreLoginData>() {
			@Override
			public void onResponse(Call<PreLoginData> call, Response<PreLoginData> response) {
				preLoginCallback.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<PreLoginData> call, Throwable t) {
				preLoginCallback.onFailure();
			}
		});
	}
}
