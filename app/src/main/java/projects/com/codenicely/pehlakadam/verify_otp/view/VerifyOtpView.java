package projects.com.codenicely.pehlakadam.verify_otp.view;

/**
 * Created by ujjwal on 17/6/17.
 */
public interface VerifyOtpView {

	void showLoading(boolean show);

	void showMessage(String message);

	void onOtpVerified(String token);

}
