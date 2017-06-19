package projects.com.codenicely.pehlakadam.verify_otp.model;

import projects.com.codenicely.pehlakadam.verify_otp.OtpCallback;

/**
 * Created by ujjwal on 17/6/17.
 */
public interface VerifyOtpHelper {
	void requestOtp(String name, String mobile,String ward, String otp, OtpCallback otpCallback);
}
