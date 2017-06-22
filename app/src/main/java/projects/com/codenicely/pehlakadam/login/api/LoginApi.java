package projects.com.codenicely.pehlakadam.login.api;


import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.login.data.LoginResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ramya on 12/10/16.
 */

public interface LoginApi {

    @GET(Urls.SUB_URL_LOGIN)
    Call<LoginResponse> requestLogin(@Query("mobile") String mobile);
}
