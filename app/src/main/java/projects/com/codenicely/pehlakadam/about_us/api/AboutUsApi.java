package projects.com.codenicely.pehlakadam.about_us.api;



import projects.com.codenicely.pehlakadam.about_us.data.AboutUsData;
import projects.com.codenicely.pehlakadam.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsApi {


    @GET(Urls.SUB_URL_ABOUT_US)
    Call<AboutUsData> getAboutUsData(@Query("lang_type")int lang_type);


}
