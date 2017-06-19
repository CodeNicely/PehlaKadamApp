package projects.com.codenicely.pehlakadam.splash_screen.api;


import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.splash_screen.data.SplashScreenData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 14/10/16.
 */
public interface SplashScreenRequestApi {


    @GET(Urls.REQUEST_SPLASH_SCREEN)
    Call<SplashScreenData> requestSplash(@Query("fcm") String fcm,
                                         @Query("access_token") String access_token);
}
