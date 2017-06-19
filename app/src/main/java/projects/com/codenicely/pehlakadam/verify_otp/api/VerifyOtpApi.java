package projects.com.codenicely.pehlakadam.verify_otp.api;

import projects.com.codenicely.pehlakadam.helper.Urls;
import projects.com.codenicely.pehlakadam.verify_otp.data.VerifyOtpResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ujjwal on 17/6/17.
 */


public interface VerifyOtpApi {
	@FormUrlEncoded
	@POST(Urls.SUB_URL_LOGIN)
	Call<VerifyOtpResponse> requestOtpData(@Field("name") String name,@Field("mobile") String mobile,
										   @Field("ward") String ward,@Field("otp") String otp);
}
