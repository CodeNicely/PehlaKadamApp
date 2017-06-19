package projects.com.codenicely.pehlakadam.verify_otp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.verify_otp.OtpCallback;
import projects.com.codenicely.pehlakadam.verify_otp.api.VerifyOtpApi;
import projects.com.codenicely.pehlakadam.verify_otp.data.VerifyOtpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ujjwal on 17/6/17.
 */
public class RetrofitOtpProvider implements VerifyOtpHelper {

	VerifyOtpApi verifyOtpApi;
	Retrofit retrofit;

	public RetrofitOtpProvider() {
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
	public void requestOtp(String name, String mobile, String ward, final String otp, final OtpCallback otpCallback) {
		verifyOtpApi = retrofit.create(VerifyOtpApi.class);
		Call<VerifyOtpResponse> verifyOtpResponseCall =verifyOtpApi.requestOtpData(name,mobile,ward,otp);
		verifyOtpResponseCall.enqueue(new Callback<VerifyOtpResponse>() {
			@Override
			public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
				otpCallback.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
				otpCallback.onFailure();
			}
		});
	}
}
