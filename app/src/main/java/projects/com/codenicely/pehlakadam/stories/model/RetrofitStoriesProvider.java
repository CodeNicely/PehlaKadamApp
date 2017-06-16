package projects.com.codenicely.pehlakadam.stories.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.stories.StoriesCallBack;
import projects.com.codenicely.pehlakadam.stories.api.StoriesRequestApi;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aman on 16/6/17.
 */

public class RetrofitStoriesProvider implements StoriesProvider{

    private StoriesRequestApi storiesRequestApi;

    public RetrofitStoriesProvider() {

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

        storiesRequestApi = retrofit.create(StoriesRequestApi.class);

    }



    @Override
    public void requestStories(String access_token, final StoriesCallBack storiesCallBack) {

        Call<StoriesData> storiesDataCall= storiesRequestApi.requestStories(access_token);
        storiesDataCall.enqueue(new Callback<StoriesData>() {
            @Override
            public void onResponse(Call<StoriesData> call, Response<StoriesData> response) {
                storiesCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StoriesData> call, Throwable throwable) {
                throwable.printStackTrace();
                storiesCallBack.onFailure("No Internet Connection");
            }
        });
    }
}
