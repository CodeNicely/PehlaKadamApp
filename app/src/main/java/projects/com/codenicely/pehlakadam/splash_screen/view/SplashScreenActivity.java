package projects.com.codenicely.pehlakadam.splash_screen.view;

/**
 * Created by aman on 11/10/16.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.helper.MyApplication;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;
import projects.com.codenicely.pehlakadam.home.views.HomeActivity;
import projects.com.codenicely.pehlakadam.splash_screen.data.SplashScreenData;
import projects.com.codenicely.pehlakadam.splash_screen.models.RetrofitSplashScreenProvider;
import projects.com.codenicely.pehlakadam.splash_screen.presenter.SplashScreenPresenter;
import projects.com.codenicely.pehlakadam.splash_screen.presenter.SplashScreenPresenterImpl;
import projects.com.codenicely.pehlakadam.welcome.view.WelcomeActivity;


public class SplashScreenActivity extends Activity implements SplashScreenView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.codenicely_logo)
    ImageView codenicely_logo;

    @BindView(R.id.logo)
    ImageView logo;

    private ImageLoader imageLoader;
    private SharedPrefs sharedPrefs;
    private SplashScreenPresenter splashScreenPresenter;
    private Handler handler;
	private Locale myLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        imageLoader = new GlideImageLoader(this);
        Log.d("ID-----------",codenicely_logo.toString());
        Glide.with(this).load(R.drawable.codenicely_logo).into(codenicely_logo);
        Glide.with(this).load(R.drawable.pk_icon10_text_green).into(logo);

        sharedPrefs = new SharedPrefs(this);
        splashScreenPresenter = new SplashScreenPresenterImpl(this,
                new RetrofitSplashScreenProvider());
	//	splashScreenPresenter = new SplashScreenPresenterImpl(this,new MockSplashScreenProvider());

		splashScreenPresenter.requestSplash(MyApplication.getFcm_token(),sharedPrefs.getAccessToken());
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {

        if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode < splashScreenData.getVersion() && splashScreenData.isCompulsory_update() != true) {


            final AlertDialog ad = new AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(false);
            ad.setTitle("App Update Available");
			Log.d("SPLASH1---","No");
            ad.setMessage("Please update the app for better experience");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    finish();
                }
            });
            ad.setButton(DialogInterface.BUTTON_NEGATIVE, "Not Now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();

                    if (!sharedPrefs.isFirstTimeLaunch()) {
//                        startActivity(new Intent(SplashScreenActivity.this, HomePage.class));
//                        finish();
                    } else {
                        startActivity(new Intent(SplashScreenActivity.this, WelcomeActivity.class));
                        finish();
                    }

                }
            });
            ad.show();


        } else if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode < splashScreenData.getVersion() && splashScreenData.isCompulsory_update() == true) {
			Log.d("SPLASH2---","No");

            final AlertDialog ad = new AlertDialog.Builder(this)

                    .create();
            ad.setCancelable(false);
            ad.setTitle("App Update Available");
            ad.setMessage("This is a compulsory Update . Please Update the app to enjoy our services");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    finish();
                }
            });
            ad.show();


        } else {



			if (!sharedPrefs.isFirstTimeLaunch()) {

				Log.d("FIRST_TIME---","No");
				startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
				finish();

			} else {

				handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
					}
				}, 3000);
//				startActivity(new Intent(SplashScreenActivity.this, WelcomeActivity.class));
//				finish();
				Log.d("FIRST_TIME---","........");
				final AlertDialog ad = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog)).create();
				ad.setCancelable(false);
				ad.setTitle("Select Your Language");
				ad.setMessage("Please select your language to enjoy our services");
				ad.setButton(DialogInterface.BUTTON_POSITIVE, "English", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ad.cancel();
						sharedPrefs.setUserLanguage(0);
						setLocale("en");
						finish();
						startActivity(new Intent(SplashScreenActivity.this, WelcomeActivity.class));
						finish();
					}
				});
				ad.setButton(DialogInterface.BUTTON_NEGATIVE, "Hindi", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ad.cancel();
						sharedPrefs.setUserLanguage(1);
						setLocale("hi");
						finish();
						startActivity(new Intent(SplashScreenActivity.this, WelcomeActivity.class));
						finish();
					}
				});

				ad.show();


			}
        }
    }
	public void setLocale(String lang) {
		myLocale = new Locale(lang);
		Resources res = getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);
	}

    @Override
    public void onFailed() {

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!sharedPrefs.isFirstTimeLaunch()) {
//                        startActivity(new Intent(SplashScreenActivity.this, HomePage.class));
//                        finish();
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, WelcomeActivity.class));
                    finish();
                }

            }
        }, 300);

    }


}