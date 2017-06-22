package projects.com.codenicely.pehlakadam.profile.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.profile.ProfileCallback;
import projects.com.codenicely.pehlakadam.profile.api.ShowProfileApi;
import projects.com.codenicely.pehlakadam.profile.data.ProfileData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ujjwal on 22/6/17.
 */
public class RetrofitProfileProvider implements ProfileProvider{
	Retrofit retrofit;
	ShowProfileApi showProfileApi;

	public RetrofitProfileProvider() {

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
	public void requestProfile(String access_token, int lang_type, final ProfileCallback profileCallback) {
		showProfileApi = retrofit.create(ShowProfileApi.class);
		Call<ProfileData> profileDataCall = showProfileApi.requestProfile(access_token,lang_type);
		profileDataCall.enqueue(new Callback<ProfileData>() {
			@Override
			public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
				profileCallback.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<ProfileData> call, Throwable t) {
				profileCallback.onFailure();
			}
		});

	}
}
