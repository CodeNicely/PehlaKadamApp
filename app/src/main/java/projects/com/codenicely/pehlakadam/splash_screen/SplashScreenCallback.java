package projects.com.codenicely.pehlakadam.splash_screen;

import android.content.pm.PackageManager;

import projects.com.codenicely.pehlakadam.splash_screen.data.SplashScreenData;


/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenCallback {

    void onSuccess(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;

    void onFailure(String error);

}
