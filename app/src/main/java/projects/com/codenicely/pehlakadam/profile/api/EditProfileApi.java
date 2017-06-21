package projects.com.codenicely.pehlakadam.profile.api;

import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.profile.data.EditProfileResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface EditProfileApi {

@FormUrlEncoded
@POST(Urls.SUB_URL_EDIT_PROFILE)
Call<EditProfileResponse> editProfile(@Field("access_token") String access_token,@Field("name")String name,
									  @Field("ward")String ward,@Field("image") String image);
}
