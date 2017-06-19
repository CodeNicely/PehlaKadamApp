package projects.com.codenicely.pehlakadam.verify_otp.presenter;

import projects.com.codenicely.pehlakadam.verify_otp.OtpCallback;
import projects.com.codenicely.pehlakadam.verify_otp.data.VerifyOtpResponse;
import projects.com.codenicely.pehlakadam.verify_otp.model.VerifyOtpHelper;
import projects.com.codenicely.pehlakadam.verify_otp.view.VerifyOtpView;

/**
 * Created by ujjwal on 17/6/17.
 */
public class OtpPresenterImpl implements VerifyOtpPresenter {
	VerifyOtpView verifyOtpView;
	VerifyOtpHelper verifyOtpHelper;

	public OtpPresenterImpl(VerifyOtpView verifyOtpView, VerifyOtpHelper verifyOtpHelper) {
		this.verifyOtpView = verifyOtpView;
		this.verifyOtpHelper = verifyOtpHelper;
	}

	@Override
	public void requestOtp(String name, String mobile, String ward, String otp) {
		verifyOtpView.showLoading(true);
		verifyOtpHelper.requestOtp(name,mobile,ward,otp, new OtpCallback() {
			@Override
			public void onSuccess(VerifyOtpResponse verifyOtpResponse) {
				verifyOtpView.showLoading(false);
				if (verifyOtpResponse.isSuccess()){
					verifyOtpView.onOtpVerified(verifyOtpResponse.getAccess_token());
				}else {
					verifyOtpView.showMessage(verifyOtpResponse.getMessage());
				}
			}

			@Override
			public void onFailure() {
			verifyOtpView.showLoading(false);
			verifyOtpView.showMessage("Unable to connect to server");
			}
		});
	}
}
