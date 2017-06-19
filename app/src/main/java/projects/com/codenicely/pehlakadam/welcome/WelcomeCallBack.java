package projects.com.codenicely.pehlakadam.welcome;



import projects.com.codenicely.pehlakadam.welcome.data.WelcomeData;

public interface WelcomeCallBack {

    void onSuccess(WelcomeData welcomeData);
    void onFailure();

}
