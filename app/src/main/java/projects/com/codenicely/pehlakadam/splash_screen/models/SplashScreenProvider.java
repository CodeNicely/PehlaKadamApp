package projects.com.codenicely.pehlakadam.splash_screen.models;


import projects.com.codenicely.pehlakadam.splash_screen.SplashScreenCallback;

/**
 * Created by aman on 13/10/16.
 */
public interface SplashScreenProvider {

    void requestSplash(String fcm,String access_token, SplashScreenCallback splashScreenCallback);
}
