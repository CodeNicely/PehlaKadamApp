package projects.com.codenicely.pehlakadam.welcome.api;


import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.welcome.data.WelcomeData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface WelcomeApi {
    @FormUrlEncoded
    @POST(Urls.SUB_URL_WELCOME)
    Call<WelcomeData> requestWelcomeData(@Field("lang_type") int lang_type);
}
