package projects.com.codenicely.pehlakadam.verify_otp.presenter;

/**
 * Created by ujjwal on 17/6/17.
 */
public interface VerifyOtpPresenter {
	void requestOtp(String name, String mobile, String ward, String otp);
}
