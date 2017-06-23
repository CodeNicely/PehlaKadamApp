package projects.com.codenicely.pehlakadam.profile.api;

import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.profile.data.ProfileData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ujjwal on 21/6/17.
 */

public interface ShowProfileApi {

@GET(Urls.SUB_URL_SHOW_PROFILE)
Call<ProfileData> requestProfile(@Query("access_token") String access_token,
								 @Query("lang_type") int lang_type);

}
