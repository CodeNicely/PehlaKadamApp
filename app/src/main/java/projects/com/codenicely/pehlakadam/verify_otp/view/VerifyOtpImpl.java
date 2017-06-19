package projects.com.codenicely.pehlakadam.verify_otp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.home.views.HomeActivity;
import projects.com.codenicely.pehlakadam.verify_otp.model.RetrofitOtpProvider;
import projects.com.codenicely.pehlakadam.verify_otp.presenter.OtpPresenterImpl;
import projects.com.codenicely.pehlakadam.verify_otp.presenter.VerifyOtpPresenter;
import projects.com.codenicely.pehlakadam.welcome.view.WelcomeActivity;

public class VerifyOtpImpl extends AppCompatActivity implements VerifyOtpView{

	String name,mobile,ward,otp;

	@BindView(R.id.edittext_otp)
	EditText editTextOtp;
	@BindView(R.id.submit_otp_button)
	Button submitButton;
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.progressBar)
	ProgressBar progressbar;
	WelcomeActivity welcomeActivity;

	private SharedPrefs sharedPrefs;

	private VerifyOtpPresenter otpPresenter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verify_otp);
		ButterKnife.bind(this);

		name = getIntent().getExtras().getString("name");
		mobile = getIntent().getExtras().getString("mobile");
		ward = getIntent().getExtras().getString("ward");
		Toast.makeText(this,name,Toast.LENGTH_SHORT).show();

		WelcomeActivity welcomeActivity =new WelcomeActivity();
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		sharedPrefs = new SharedPrefs(this);
		otpPresenter = new OtpPresenterImpl(this,new RetrofitOtpProvider());

		mobile = getIntent().getExtras().getString("mobile");

		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				otp = editTextOtp.getText().toString();
				otpPresenter.requestOtp(name, mobile,ward,otp);
				submitButton.setEnabled(false);
				submitButton.setClickable(false);
			}
		});
	}

	@Override
	public void showLoading(boolean show) {
		submitButton.setEnabled(true);
		submitButton.setClickable(true);
		if (show) {
			progressbar.setVisibility(View.VISIBLE);
			submitButton.setClickable(false);

		} else {
			progressbar.setVisibility(View.INVISIBLE);
			submitButton.setEnabled(true);
		}
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onOtpVerified(String token) {
		Log.d("res", token);
		sharedPrefs.setAccessToken(token);
		sharedPrefs.setFirstTimeLaunch(false);
		Intent a = new Intent(VerifyOtpImpl.this, HomeActivity.class);
		startActivity(a);
		finish();

//		loginScreenActivity.loginScreenActivity.finish();
//		welcomeScreenActivity.welcomeScreenActivity.finish();
	}
}
