package projects.com.codenicely.pehlakadam.welcome.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;
import java.util.Timer;

import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.verify_otp.view.VerifyOtpImpl;
import projects.com.codenicely.pehlakadam.login.data.WardDetails;
import projects.com.codenicely.pehlakadam.welcome.data.WelcomePageDetails;
import projects.com.codenicely.pehlakadam.welcome.model.RetrofitWelcomeProvider;
import projects.com.codenicely.pehlakadam.welcome.presenter.WelcomePresenter;
import projects.com.codenicely.pehlakadam.welcome.presenter.WelcomePresenterImpl;

public class WelcomeActivity extends AppCompatActivity implements WelcomeView{

    private ViewPager viewPager;
    private SharedPrefs sharedPrefs;
    private LinearLayout dotsLayout;
    private ViewPagerAdapter viewPagerAdapter;
    private ProgressBar progressBar;
    private WelcomePresenter welcomePresenter;
    Timer swipeTimer;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sharedPrefs=new SharedPrefs(this);
        if(!sharedPrefs.isFirstTimeLaunch()){
			setHome();
		}

		progressBar = (ProgressBar)findViewById(R.id.first_progressBar);
        frameLayout = (FrameLayout) findViewById(R.id.welcome_layout);
        viewPagerAdapter = new ViewPagerAdapter(this,this);
        viewPager=(ViewPager)findViewById(R.id.first_viewPager);
//        welcomePresenter= new WelcomePresenterImpl(this, new MockWelcomeProvider());
        welcomePresenter= new WelcomePresenterImpl(this, new RetrofitWelcomeProvider());

        welcomePresenter.requestWelcomeData(sharedPrefs.getUserLanguage());
        viewPagerAdapter = new ViewPagerAdapter(this,this);
        viewPager.setAdapter(viewPagerAdapter);

        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
//                addBottomDots(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        };
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

//        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                // do transformation here
//                final float normalizedposition = Math.abs(Math.abs(position) - 1);
//                page.setScaleX(normalizedposition / 2 + 0.5f);
//                page.setScaleY(normalizedposition / 2 + 0.5f);
//            }
//        });

        swipeTimer = new Timer();

//        swipeTimer.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (viewPager.getCurrentItem() + 1 == 4) {
//                            viewPager.setCurrentItem(-1);
//                            //  currentPage = 0;
//                        } else {
//                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
//                        }
//                    }
//                });
//            }
//        }, 3200, 3200);

    }

    @Override
    public void showMessage(String error) {

    }

    @Override
    public void showProgressBar(boolean show) {
        if(show)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setData(List<WelcomePageDetails> pageDetails, List<WardDetails> wardDetailsList) {
        viewPagerAdapter.setData(pageDetails,wardDetailsList);
        viewPagerAdapter.notifyDataSetChanged();
        if (!sharedPrefs.isFirstTimeLaunch()){
            viewPager.setCurrentItem(wardDetailsList.size());
        }
    }

	@Override
	public void onLoginSuccess(String name, String mobile, String ward) {
		Intent intent = new Intent(this, VerifyOtpImpl.class);
		intent.putExtra("name",name);
		intent.putExtra("mobile",mobile);
		intent.putExtra("ward",ward);
		startActivity(intent);
	}

	public void requestLogin(String name,String mobile,String ward){
        welcomePresenter.requestLogin(name,mobile,ward);
    }


    public void setHome() {
//        Intent in=new Intent(WelcomeActivity.this, Home.class);
//        startActivity(in);
//        finish();
    }

}
