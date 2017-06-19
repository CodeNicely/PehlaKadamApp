package projects.com.codenicely.pehlakadam.welcome.presenter;


import projects.com.codenicely.pehlakadam.welcome.LoginCallback;
import projects.com.codenicely.pehlakadam.welcome.WelcomeCallBack;
import projects.com.codenicely.pehlakadam.welcome.data.LoginResponse;
import projects.com.codenicely.pehlakadam.welcome.data.WelcomeData;
import projects.com.codenicely.pehlakadam.welcome.model.WelcomeProvider;
import projects.com.codenicely.pehlakadam.welcome.view.WelcomeView;

public class WelcomePresenterImpl implements WelcomePresenter {


    private WelcomeView welcomeView;
    private WelcomeProvider welcomeProvider;

    public WelcomePresenterImpl(WelcomeView welcomeView, WelcomeProvider welcomeProvider) {
        this.welcomeView = welcomeView;
        this.welcomeProvider = welcomeProvider;
    }

    @Override
    public void requestWelcomeData(int lang_type) {

        welcomeProvider.requestWelcomeData(lang_type,new WelcomeCallBack() {
            @Override
            public void onSuccess(WelcomeData welcomeData) {
                if(welcomeData.isSuccess())
                {
                    welcomeView.setData(welcomeData.getWelcome_page(),welcomeData.getWard_list());
                    welcomeView.showMessage("Success");
                    welcomeView.showProgressBar(false);
                }
                else
                {
                    welcomeView.showMessage("Try Again Sometime Later");
                    welcomeView.showProgressBar(false);
                }
            }

            @Override
            public void onFailure() {
                    welcomeView.showMessage("Failed");
            }
        });

    }

    @Override
    public void requestLogin(final String name, final String mobile, final String ward) {
        welcomeView.showProgressBar(true);
        welcomeProvider.requestLogin(mobile, new LoginCallback() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                welcomeView.showProgressBar(false);
                if (loginResponse.isSuccess()){
                    welcomeView.onLoginSuccess(name,mobile,ward);
                }else {
                    welcomeView.showMessage(loginResponse.getMessage());
                }
            }

            @Override
            public void onFailure() {
                welcomeView.showProgressBar(false);
                welcomeView.showMessage("Unable to connect to Server");
            }
        });
    }
}
