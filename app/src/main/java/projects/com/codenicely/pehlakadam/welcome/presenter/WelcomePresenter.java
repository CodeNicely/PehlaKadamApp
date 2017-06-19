package projects.com.codenicely.pehlakadam.welcome.presenter;

/**
 * Created by aman on 4/2/17.
 */

public interface WelcomePresenter {



    void requestWelcomeData(int lang_type);
    void requestLogin(String name,String mobile,String ward);
}
