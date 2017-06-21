package projects.com.codenicely.pehlakadam.login.presenter;

import projects.com.codenicely.pehlakadam.login.PreLoginCallback;
import projects.com.codenicely.pehlakadam.login.data.LoginResponse;
import projects.com.codenicely.pehlakadam.login.data.PreLoginData;
import projects.com.codenicely.pehlakadam.login.model.LoginHelper;
import projects.com.codenicely.pehlakadam.login.view.LoginView;
import projects.com.codenicely.pehlakadam.welcome.LoginCallback;

/**
 * Created by ujjwal on 21/6/17.
 */

public class LoginPresenterImpl implements LoginPresenter {
	LoginView loginView;
	LoginHelper loginHelper;

	public LoginPresenterImpl(LoginView loginView, LoginHelper loginHelper) {
		this.loginView = loginView;
		this.loginHelper = loginHelper;
	}


	@Override
	public void requestPreLoginData() {
		loginView.showProgressBar(false);
		loginHelper.requestPreLoginData(new PreLoginCallback() {
			@Override
			public void onSuccess(PreLoginData preLoginData) {
				loginView.showProgressBar(false);
				if (preLoginData.isSuccess()){
					loginView.setData(preLoginData.getWard_list());
				}else {
					loginView.showMessage(preLoginData.getMessage());
				}
			}

			@Override
			public void onFailure() {
				loginView.showProgressBar(false);
				loginView.showMessage("Unable to connect to server");
			}
		});
	}

	@Override
	public void requestLogin(final String name, final String mobile, final String ward) {
		loginView.showProgressBar(false);
		loginHelper.requestLogin(mobile, new LoginCallback() {
			@Override
			public void onSuccess(LoginResponse loginResponse) {
				loginView.showProgressBar(false);
				if (loginResponse.isSuccess()){
					loginView.onLoginSuccess(name,mobile,ward);
				}else {
					loginView.showMessage(loginResponse.getMessage());
				}

			}

			@Override
			public void onFailure() {
				loginView.showProgressBar(false);
				loginView.showMessage("Unable to connect to server");
			}
		});

	}
}
