package projects.com.codenicely.pehlakadam.verify_otp.data;

/**
 * Created by ujjwal on 17/6/17.
 */
public class VerifyOtpResponse {
	private boolean success;
	private String message;
	private String access_token;

	public VerifyOtpResponse(boolean success, String message, String access_token) {
		this.success = success;
		this.message = message;
		this.access_token = access_token;
	}

	public String getAccess_token() {

		return access_token;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
