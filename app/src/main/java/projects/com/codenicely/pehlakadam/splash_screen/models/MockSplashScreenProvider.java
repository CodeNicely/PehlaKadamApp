package projects.com.codenicely.pehlakadam.splash_screen.models;


import android.content.pm.PackageManager;
import android.os.Handler;

import projects.com.codenicely.pehlakadam.splash_screen.SplashScreenCallback;
import projects.com.codenicely.pehlakadam.splash_screen.data.SplashScreenData;

/**
 * Created by ujjwal on 19/6/17.
 */
public class MockSplashScreenProvider implements SplashScreenProvider {

	private SplashScreenData getMockPageDetails(){

		SplashScreenData splashScreenData = new SplashScreenData(true,"Success",1,false);

		return splashScreenData;

	}

	@Override
	public void requestSplash(String fcm, String access_token, final SplashScreenCallback splashScreenCallback) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				try {
					splashScreenCallback.onSuccess(getMockPageDetails());
				} catch (PackageManager.NameNotFoundException e) {
					e.printStackTrace();
				}
			}
		},500);

	}
}
