package projects.com.codenicely.pehlakadam.welcome;

import projects.com.codenicely.pehlakadam.login.data.LoginResponse;

/**
 * Created by ujjwal on 17/6/17.
 */
public interface LoginCallback {
	void onSuccess(LoginResponse loginResponse);
	void onFailure();
}
