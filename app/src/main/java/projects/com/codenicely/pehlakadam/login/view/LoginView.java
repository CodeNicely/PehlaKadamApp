package projects.com.codenicely.pehlakadam.login.view;

import java.util.List;

import projects.com.codenicely.pehlakadam.login.data.WardDetails;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface LoginView {

	void showMessage(String error);

	void showProgressBar(boolean show);
	void setData(List<WardDetails> wardDetailsList);

	void onLoginSuccess(String name, String mobile, String ward);

}
