package projects.com.codenicely.pehlakadam.welcome.api;


import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.welcome.data.WelcomeData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WelcomeApi {
    @GET(Urls.SUB_URL_WELCOME)
    Call<WelcomeData> requestWelcomeData(@Query("lang_type") int lang_type);
}
