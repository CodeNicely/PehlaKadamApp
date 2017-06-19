package projects.com.codenicely.pehlakadam.welcome.model;


import projects.com.codenicely.pehlakadam.welcome.LoginCallback;
import projects.com.codenicely.pehlakadam.welcome.WelcomeCallBack;

public interface WelcomeProvider {

    void requestWelcomeData(int lang_type,WelcomeCallBack welcomeCallBack);
    void requestLogin(String mobile,LoginCallback loginCallback);
}
