package projects.com.codenicely.pehlakadam.login.api;

import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.login.data.PreLoginData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface PreLoginApi {
	@GET(Urls.SUB_URL_WELCOME)
	Call<PreLoginData> requestPreLoginData();
}
