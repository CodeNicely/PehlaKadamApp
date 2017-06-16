package projects.com.codenicely.pehlakadam.stories.api;

import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 16/6/17.
 */

public interface StoriesRequestApi {


    @GET(Urls.REQUEST_STORIES)
    Call<StoriesData> requestStories(@Query("access_token") String access_token);
}
