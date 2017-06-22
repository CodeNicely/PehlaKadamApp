package projects.com.codenicely.pehlakadam.feedback.api;

import projects.com.codenicely.pehlakadam.feedback.data.FeedbackResponse;
import projects.com.codenicely.pehlakadam.gallery.model.GalleryData;
import projects.com.codenicely.pehlakadam.helper.Urls;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ujjwal on 20/6/17.
 */

public interface FeedbackApi {

	@FormUrlEncoded
	@POST(Urls.SUB_URL_FEEDBACK)
	Call<FeedbackResponse> getFeedback(
			@Field("feedback") String feedback,@Field("access_token") String access_token);

}
