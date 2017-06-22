package projects.com.codenicely.pehlakadam.join_us.api;

import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.join_us.model.data.JoinUsData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 20/6/17.
 */

public interface JoinUsApi {

    @GET(Urls.REQUEST_JOIN_US)
    Call<JoinUsData> requestJoinUs(@Query("access_token") String access_token,
                                   @Query("mobile") String mobile,
                                   @Query("email") String email,
                                   @Query("description") String desc);
}
