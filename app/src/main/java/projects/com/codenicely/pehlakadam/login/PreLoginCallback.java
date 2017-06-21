package projects.com.codenicely.pehlakadam.login;

import projects.com.codenicely.pehlakadam.login.data.PreLoginData;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface PreLoginCallback {

	void onSuccess(PreLoginData preLoginData);
	void onFailure();
}
