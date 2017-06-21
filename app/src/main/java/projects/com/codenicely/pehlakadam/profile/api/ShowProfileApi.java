package projects.com.codenicely.pehlakadam.profile.api;

import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.profile.data.ProfileData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ujjwal on 21/6/17.
 */

public interface ShowProfileApi {
	@GET(Urls.SUB_URL_LOGIN)
	Call<ProfileData> requestLogin(@Query("mobile") String mobile);

}
