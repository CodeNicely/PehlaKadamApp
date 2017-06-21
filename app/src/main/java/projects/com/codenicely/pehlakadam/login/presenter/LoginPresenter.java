package projects.com.codenicely.pehlakadam.login.presenter;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface LoginPresenter {
	void requestPreLoginData();
	void requestLogin(String name,String mobile,String ward);
}
