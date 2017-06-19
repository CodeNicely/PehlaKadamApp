package projects.com.codenicely.pehlakadam.splash_screen.view;


import android.content.pm.PackageManager;

import projects.com.codenicely.pehlakadam.splash_screen.data.SplashScreenData;


/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenView {

    void showMessage(String message);

    void showProgressBar(boolean show);

    void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;

    void onFailed();
}
