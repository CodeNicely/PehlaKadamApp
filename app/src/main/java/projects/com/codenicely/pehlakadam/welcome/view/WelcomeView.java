package projects.com.codenicely.pehlakadam.welcome.view;

import java.util.List;

import projects.com.codenicely.pehlakadam.login.data.WardDetails;
import projects.com.codenicely.pehlakadam.welcome.data.WelcomePageDetails;

public interface WelcomeView {

    void showMessage(String error);

    void showProgressBar(boolean show);

    void setData(List<WelcomePageDetails> pageDetails, List<WardDetails> wardDetailsList);

    void onLoginSuccess(String name, String mobile, String ward);
}
