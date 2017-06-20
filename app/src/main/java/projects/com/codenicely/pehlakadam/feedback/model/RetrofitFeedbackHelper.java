package projects.com.codenicely.pehlakadam.feedback.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.feedback.FeedbackCallback;
import projects.com.codenicely.pehlakadam.feedback.api.FeedbackApi;
import projects.com.codenicely.pehlakadam.feedback.data.FeedbackResponse;
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
public class RetrofitFeedbackHelper implements FeedbackHelper{
	private FeedbackApi feedbackApi;
	private Call<FeedbackResponse> feedbackResponseCall;

	public RetrofitFeedbackHelper() {

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
		feedbackApi = retrofit.create(FeedbackApi.class);

	}


	@Override
	public void sendFeedback(String access_token, String feedback, final FeedbackCallback feedbackCallback) {
		feedbackResponseCall = feedbackApi.getFeedback(feedback,access_token);
		feedbackResponseCall.enqueue(new Callback<FeedbackResponse>() {
			@Override
			public void onResponse(Call<FeedbackResponse> call, Response<FeedbackResponse> response) {
				feedbackCallback.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<FeedbackResponse> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
