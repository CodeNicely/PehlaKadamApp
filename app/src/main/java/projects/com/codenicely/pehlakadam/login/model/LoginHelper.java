package projects.com.codenicely.pehlakadam.login.model;

import projects.com.codenicely.pehlakadam.login.PreLoginCallback;
import projects.com.codenicely.pehlakadam.welcome.LoginCallback;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface LoginHelper {
	void requestLogin(String mobile,LoginCallback loginCallback);
	void requestPreLoginData(PreLoginCallback preLoginCallback);

}
