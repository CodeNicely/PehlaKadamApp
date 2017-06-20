package projects.com.codenicely.pehlakadam.dustbin.api;

import projects.com.codenicely.pehlakadam.dustbin.data.DustbinData;
import projects.com.codenicely.pehlakadam.helper.Urls;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ujjwal on 20/6/17.
 */
public interface DustbinApi {

	@GET(Urls.SUB_URL_DUSTBIN)
	Call<DustbinData> getDustbinData(@Query("latitude") Double latitude,@Query("longitude") Double longitude);

}
