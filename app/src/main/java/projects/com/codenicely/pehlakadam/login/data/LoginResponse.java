package projects.com.codenicely.pehlakadam.login.data;

/**
 * Created by ujjwal on 17/6/17.
 */
public class LoginResponse {
	private boolean success;
	private String message;

	public LoginResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
