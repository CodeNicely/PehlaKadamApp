package projects.com.codenicely.pehlakadam.dustbin.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.dustbin.DustbinCallback;
import projects.com.codenicely.pehlakadam.dustbin.api.DustbinApi;
import projects.com.codenicely.pehlakadam.dustbin.data.DustbinData;
import projects.com.codenicely.pehlakadam.helper.Urls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ujjwal on 20/6/17.
 */
public class RetrofitDustbinProvider implements DustbinProvider {
	private DustbinApi dustbinApi;
	private Call<DustbinData> dustbinDataCall;

	public RetrofitDustbinProvider() {
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
		dustbinApi = retrofit.create(DustbinApi.class);
	}


	@Override
	public void getDustbinData(Double latitude,Double longitude,final DustbinCallback dustbinCallback) {
		dustbinDataCall = dustbinApi.getDustbinData(latitude,longitude);
		dustbinDataCall.enqueue(new Callback<DustbinData>() {
			@Override
			public void onResponse(Call<DustbinData> call, Response<DustbinData> response) {
				dustbinCallback.onSuccess(response.body());
			}
			@Override
			public void onFailure(Call<DustbinData> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
