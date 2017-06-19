package projects.com.codenicely.pehlakadam.splash_screen.presenter;

import android.content.pm.PackageManager;

import projects.com.codenicely.pehlakadam.splash_screen.SplashScreenCallback;
import projects.com.codenicely.pehlakadam.splash_screen.data.SplashScreenData;
import projects.com.codenicely.pehlakadam.splash_screen.models.RetrofitSplashScreenProvider;
import projects.com.codenicely.pehlakadam.splash_screen.models.SplashScreenProvider;
import projects.com.codenicely.pehlakadam.splash_screen.view.SplashScreenActivity;
import projects.com.codenicely.pehlakadam.splash_screen.view.SplashScreenView;


/**
 * Created by aman on 13/10/16.
 */


public class SplashScreenPresenterImpl implements SplashScreenPresenter{

    private static final String LOG_TAG = "SplashScreenActivity";
    private SplashScreenView splashScreenView;

    private SplashScreenProvider splashScreenProvider;

    public SplashScreenPresenterImpl(SplashScreenView splashScreenView, SplashScreenProvider splashScreenProvider) {
        this.splashScreenView = splashScreenView;
        this.splashScreenProvider = splashScreenProvider;
    }

    @Override
    public void requestSplash(String fcm,String access_token) {
        splashScreenView.showProgressBar(true);

        splashScreenProvider.requestSplash(fcm, access_token,new SplashScreenCallback() {
            @Override
            public void onSuccess(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {

                //Log.d(LOG_TAG,"Reached");
                //splashScreenView.showProgressBar(false);
                if (splashScreenData.isSuccess()) {
                    splashScreenView.onVersionReceived(splashScreenData);
                    splashScreenView.showProgressBar(false);
                } else {
                    //splashScreenView.version_check(splashScreenData);
                    splashScreenView.onFailed();
                    splashScreenView.showMessage(splashScreenData.getMessage());
                    splashScreenView.showProgressBar(false);
                }

            }

            @Override
            public void onFailure(String error) {
                splashScreenView.showProgressBar(false);
                splashScreenView.showMessage("No Internet Connection");

            }
        });


    }
}
