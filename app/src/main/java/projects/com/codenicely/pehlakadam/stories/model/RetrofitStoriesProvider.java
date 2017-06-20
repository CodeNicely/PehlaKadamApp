package projects.com.codenicely.pehlakadam.stories.model;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.helper.utils.BitmapUtils;
import projects.com.codenicely.pehlakadam.helper.utils.FileUtils;
import projects.com.codenicely.pehlakadam.helper.utils.UriUtils;
import projects.com.codenicely.pehlakadam.stories.StoriesCallBack;
import projects.com.codenicely.pehlakadam.stories.StoriesLikeShareCallBack;
import projects.com.codenicely.pehlakadam.stories.api.StoriesRequestApi;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesImageData;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesLikeShareData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by aman on 16/6/17.
 */

public class RetrofitStoriesProvider implements StoriesProvider{

    private StoriesRequestApi storiesRequestApi;
    private Context context;

    public RetrofitStoriesProvider(Context context) {

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

    @Override
    public void requestLikeShare(String access_token, int story_id,
                                 int button_id, final StoriesLikeShareCallBack storiesLikeShareCallBack) {
        Call<StoriesLikeShareData> storiesLikeShareDataCall;
        if(button_id==0)//LIKE BUTTON CLICK
        {
            storiesLikeShareDataCall=storiesRequestApi.requestLikeStory(access_token,story_id);
        }
        else//SHARE BUTTON CLICK
        {
            storiesLikeShareDataCall=storiesRequestApi.requestShareStory(access_token,story_id);
        }

        storiesLikeShareDataCall.enqueue(new Callback<StoriesLikeShareData>() {
            @Override
            public void onResponse(Call<StoriesLikeShareData> call, Response<StoriesLikeShareData> response) {
                storiesLikeShareCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StoriesLikeShareData> call, Throwable throwable) {
                throwable.printStackTrace();
                storiesLikeShareCallBack.onFailure("No Internet Connection");
            }
        });
    }

    @Override
    public Observable<StoriesImageData> addStories(String access_token, String title,
                                                   String description, Uri image)
                                                        throws IOException {

        RequestBody access_token1=RequestBody.create(
                MediaType.parse("multipart/form-data"), access_token);
        RequestBody title1=RequestBody.create(
                MediaType.parse("multipart/form-data"), title);
        RequestBody description1=RequestBody.create(
                MediaType.parse("multipart/form-data"), description);
        if (image!=null)
        {
            File imageFile = FileUtils.BitmapToFileConverter(context,
                            BitmapUtils.filePathToBitmapConverter(
                            UriUtils.uriToFilePathConverter(context,image)));
            RequestBody fbody= RequestBody.create(MediaType.parse("multipart/form-data"),imageFile);

            MultipartBody.Part image1=
                            MultipartBody.Part.createFormData("image",imageFile.getName(),fbody);

            return storiesRequestApi.addStories(access_token1,title1,description1,image1);

        }

        return null;
    }


}
