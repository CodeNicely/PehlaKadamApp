package projects.com.codenicely.pehlakadam.welcome.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


import java.util.ArrayList;
import java.util.List;

import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;
import projects.com.codenicely.pehlakadam.home.views.HomeActivity;
import projects.com.codenicely.pehlakadam.welcome.data.WardDetails;
import projects.com.codenicely.pehlakadam.welcome.data.WelcomePageDetails;

/**
 * Created by aman on 4/2/17.
 */

public class ViewPagerAdapter extends PagerAdapter {

	private static final String SELECT_WARD = "Select Ward";
	private Spinner spinnerWard;
	private Context context;
    private TextView quote;
	private ImageView imageView;
	private ProgressBar progressBar;
	private EditText editTextName,editTextMobile;
	private String name,mobile,ward;
    private Button forward_button,loginButton;
    private List<WelcomePageDetails> welcomePageDetailsList = new ArrayList<>();
	private List<WardDetails> wardDetailsList = new ArrayList<>();
    private LayoutInflater layoutInflater;
	int size;
	ImageLoader imageLoader;
	private ArrayAdapter<String> ward_array_adapter;
	private WelcomeActivity welcomeActivity;
	private SharedPrefs sharedPrefs;


	public ViewPagerAdapter(Context context,WelcomeActivity welcomeActivity) {
        this.context = context;
		this.welcomeActivity = welcomeActivity;
		this.sharedPrefs =new SharedPrefs(context);
    }

    public void setData(List<WelcomePageDetails> pageDetailsList,List<WardDetails> wardDetailsList){
        this.welcomePageDetailsList =pageDetailsList;
		this.wardDetailsList=wardDetailsList;
		size=welcomePageDetailsList.size()+1;
		Log.d("SIZE----",wardDetailsList.size()+"");


	}

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);


        if (position < welcomePageDetailsList.size() && welcomePageDetailsList.size()!=0) {

            View view = layoutInflater.inflate(R.layout.viewpager_item, container, false);
            container.addView(view);
            WelcomePageDetails welcomeDetails = welcomePageDetailsList.get(position);
         	forward_button = (Button) view.findViewById(R.id.welcome_button);
			quote = (TextView) view.findViewById(R.id.quote);
         	imageView= (ImageView) view.findViewById(R.id.image_view);
			progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            YoYo.with(Techniques.ZoomInLeft)
                    .duration(1500)
                    .playOn(quote);
            if (position == welcomePageDetailsList.size() - 1)
                forward_button.setVisibility(View.INVISIBLE);
            else
                forward_button.setVisibility(View.INVISIBLE);
            forward_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((WelcomeActivity) context).setHome();
                }
            });
			imageLoader = new GlideImageLoader(context);
			imageLoader.loadImage(welcomeDetails.getImage(),imageView,progressBar);

			quote.setText(welcomeDetails.getQuote());

			return view;

        }
		else {
			View view = layoutInflater.inflate(R.layout.login_item, container, false);
			container.addView(view);

			editTextName = (EditText) view.findViewById(R.id.input_name);
			editTextMobile = (EditText) view.findViewById(R.id.input_mobile);
			spinnerWard = (Spinner) view.findViewById(R.id.spinner_ward);
			loginButton = (Button) view.findViewById(R.id.login_button);
			Button skip_button = (Button) view.findViewById(R.id.skip_button);



			ward_array_adapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item);
			ward_array_adapter.add(SELECT_WARD);

			for (int i = 0; i < wardDetailsList.size(); i++) {
				WardDetails wardDetails = wardDetailsList.get(i);
				ward_array_adapter.add(wardDetails.getName());
			}

			spinnerWard.setAdapter(ward_array_adapter);
			skip_button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					sharedPrefs.setFirstTimeLaunch(false);
					Intent i = new Intent(context, HomeActivity.class);
					((WelcomeActivity)context).startActivity(i);
					((WelcomeActivity)context).finish();
				}
			});

			loginButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					name = editTextName.getText().toString();
					mobile = editTextMobile.getText().toString();
					ward = spinnerWard.getSelectedItem().toString();

					if (name.equals("") || name.equals(null)) {
						editTextName.setError("Please enter Name");
						editTextName.requestFocus();
					} else if (mobile.equals("") || mobile.equals(null)) {
						editTextMobile.setError("Please enter mobile");
						editTextMobile.requestFocus();
					} else if (mobile.length() != 10) {
						editTextMobile.setError("Mobile number length should be 10");
						editTextMobile.requestFocus();
					} else if (ward.equals(SELECT_WARD)) {
						Toast.makeText(context, "Please select a ward",
								Toast.LENGTH_SHORT).show();
						spinnerWard.requestFocus();
					}else {
						welcomeActivity.requestLogin(name,mobile,ward);
					}
				}
			});

			return view;
        }
    }

    @Override
    public int getCount() {
        return size;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
