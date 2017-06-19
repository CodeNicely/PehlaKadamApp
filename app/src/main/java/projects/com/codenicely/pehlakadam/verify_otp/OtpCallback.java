package projects.com.codenicely.pehlakadam.verify_otp;

import projects.com.codenicely.pehlakadam.verify_otp.data.VerifyOtpResponse;

/**
 * Created by ujjwal on 17/6/17.
 */
public interface OtpCallback {
	void onSuccess(VerifyOtpResponse verifyOtpResponse);
	void onFailure();
}
