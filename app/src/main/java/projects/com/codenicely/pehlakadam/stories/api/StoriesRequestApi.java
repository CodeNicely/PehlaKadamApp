package projects.com.codenicely.pehlakadam.stories.api;

import android.graphics.Bitmap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesLikeShareData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by aman on 16/6/17.
 */

public interface StoriesRequestApi {


    @GET(Urls.REQUEST_STORIES)
    Call<StoriesData> requestStories(@Query("access_token") String access_token);

    @FormUrlEncoded
    @POST(Urls.REQUEST_STORIES_LIKE)
    Call<StoriesLikeShareData> requestLikeStory(@Field("access_token") String access_token,
                                                @Field("story_id") int story_id
                                                );

    @FormUrlEncoded
    @POST(Urls.REQUEST_STORIES_SHARE)
    Call<StoriesLikeShareData> requestShareStory(@Field("access_token") String access_token,
                                                @Field("story_id") int story_id
    );

    @Multipart
    @FormUrlEncoded
    @POST(Urls.REQUEST_STORIES)
    Observable<StoriesLikeShareData> addStories(@Part("access_token") RequestBody access_token,
                                                @Part("title") RequestBody title,
                                                @Part("description") RequestBody description,
                                                @Part("image") MultipartBody.Part image);
}

