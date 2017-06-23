package projects.com.codenicely.pehlakadam.home.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;

import butterknife.BindView;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.about_us.view.AboutUsFragment;
import projects.com.codenicely.pehlakadam.contact_us.view.ContactUsFragment;
import projects.com.codenicely.pehlakadam.developers.view.DeveloperFragment;
import projects.com.codenicely.pehlakadam.feedback.model.RetrofitFeedbackHelper;
import projects.com.codenicely.pehlakadam.feedback.presenter.FeedbackPresenter;
import projects.com.codenicely.pehlakadam.feedback.presenter.FeedbackPresenterImpl;
import projects.com.codenicely.pehlakadam.feedback.view.FeedbackView;
import projects.com.codenicely.pehlakadam.gallery_video.model.data.ContentDetails;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.helper.Toaster;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;
import projects.com.codenicely.pehlakadam.image_viewer.ImageViewerActivity;
import projects.com.codenicely.pehlakadam.join_us.model.RetrofitJoinUsProvider;
import projects.com.codenicely.pehlakadam.join_us.presenter.JoinUsPresenter;
import projects.com.codenicely.pehlakadam.join_us.presenter.JoinUsPresenterImpl;
import projects.com.codenicely.pehlakadam.join_us.view.JoinUsView;
import projects.com.codenicely.pehlakadam.profile.view.ProfileFragment;
import projects.com.codenicely.pehlakadam.video_player.VideoPlayer;
import projects.com.codenicely.pehlakadam.welcome.view.WelcomeActivity;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, JoinUsView,FeedbackView{

    private SharedPrefs sharedPrefs;
    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPagerAdapter viewPagerAdapter;
    private Context context;
    private ProgressBar progressBar;
    private JoinUsPresenter joinUsPresenter;
	private FeedbackPresenter feedbackPresenter;
    private Toaster toaster;
    private ImageLoader imageLoader;
    private static Dialog dialog;

    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().show();
        sharedPrefs=new SharedPrefs(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Glide.with(this).load(R.drawable.pk_icon10_text_green).into(imageView);
        context=this;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

		navigationView.setNavigationItemSelectedListener(this);
		if (!sharedPrefs.isLoggedIn()){

			navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
		}
		joinUsPresenter= new JoinUsPresenterImpl(context,this,new RetrofitJoinUsProvider());
        feedbackPresenter = new FeedbackPresenterImpl(this,new RetrofitFeedbackHelper());
		Log.d("HomeActivity----","1"+joinUsPresenter.toString());
        toaster=new Toaster(context);
        imageLoader = new GlideImageLoader(context);
        dialog = new Dialog(context, R.style.dialogstyle);
        addFragment(new HomeFragment(),"Pehla Kadam");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//            navigationView.getMenu().getItem(0).setChecked(true);
            super.onBackPressed();

        } else {

            final android.support.v7.app.AlertDialog ad = new android.support.v7.app.AlertDialog.Builder(this)
                                                                  .create();
            ad.setCancelable(false);
            ad.setTitle("Exit ?");
            ad.setMessage("Do you really want to exit ?");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    finish();
                }
            });
            ad.setButton(DialogInterface.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();

                }
            });
            ad.show();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent i = new Intent(this,HomeActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_profile) {

            ProfileFragment profileFragment = new ProfileFragment();
            setFragment(profileFragment,"Profile");
        } else if (id == R.id.nav_login) {

            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_feedback) {
            showFeedbackDialogBox();

        } else if (id == R.id.nav_join_us) {
            if(sharedPrefs.isLoggedIn())
            {
                showDialogBox();
            }else{
                toaster.showMessage("Please Login!!!");
            }

        } else if (id == R.id.nav_about_us) {
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            setFragment(aboutUsFragment,"About Us");

        } else if (id == R.id.nav_contact_us) {

            ContactUsFragment contactUsFragment = new ContactUsFragment();
            setFragment(contactUsFragment,"Contact Us");
        } else if (id == R.id.nav_developers) {

			DeveloperFragment developerFragment = new DeveloperFragment();
			setFragment(developerFragment,"Developers");
			getSupportActionBar().hide();
		}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setFragment(Fragment fragment, String title) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.home_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }
    public void addFragment(Fragment fragment, String title) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.home_layout, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }

    public void playVideo(String video_url) {
        Log.d("res","2");
        Intent vid=new Intent(HomeActivity.this, VideoPlayer.class);
        vid.putExtra("url",video_url);
        startActivity(vid);
    }

    public void showImage(List<ContentDetails> contentDetailses, int position) {
        ArrayList<String> imageUrlList=new ArrayList<>();
        for ( int i=0;i<contentDetailses.size();i++){
            if(contentDetailses.get(i).getType()==1)
                imageUrlList.add(contentDetailses.get(i).getImage_url());
        }
        Intent image_viewer=new Intent(HomeActivity.this, ImageViewerActivity.class);

        image_viewer.putStringArrayListExtra("list", imageUrlList);
        image_viewer.putExtra("position", position);
        startActivity(image_viewer);
    }

    public void showDialogBox(){

//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        ImageView imageView = (ImageView)dialog.findViewById(R.id.profile_image) ;
        ProgressBar progressBar = (ProgressBar)dialog.findViewById(R.id.image_bar);
        final TextView name = (TextView) dialog.findViewById(R.id.name);
        final EditText mobile = (EditText) dialog.findViewById(R.id.mobile);
        final EditText email = (EditText) dialog.findViewById(R.id.email);
        final EditText address = (EditText) dialog.findViewById(R.id.ward);
        final EditText join_reason = (EditText) dialog.findViewById(R.id.join_reason);
        Button btn_ok = (Button) dialog.findViewById(R.id.btn_submit);
        Button btn_cancel= (Button) dialog.findViewById(R.id.btn_cancel);
        if ( sharedPrefs.getProfileImage().equals("profile_image" )|| sharedPrefs.getProfileImage().equals("") ) {

            imageView.setImageResource(R.drawable.ic_profile);
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            imageLoader.loadImage(sharedPrefs.getProfileImage(), imageView, progressBar);
        }
        name.setText(sharedPrefs.getUsername());
        mobile.setText(sharedPrefs.getMobile());
        email.setText(sharedPrefs.getEmail());
        address.setText(sharedPrefs.getWard());
        address.setFocusable(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1,email1,mobile1,reason;
                name1= name.getText().toString();
                email1= email.getText().toString();
                mobile1= mobile.getText().toString();
                reason= join_reason.getText().toString();

                if(name1.equals("") || name1.equals(null)) {
                    name.setError("Please enter Name");
                    name.requestFocus();
                }else if (mobile1.equals("") || mobile1.equals(null)) {
                    mobile.setError("Please enter mobile");
                    mobile.requestFocus();
                }else if (mobile1.length() != 10) {
                    mobile.setError("Mobile number length should be 10");
                    mobile.requestFocus();
                }else if (email1.equals("")) {
                    email.setError("Please enter Email");
                    email.requestFocus();
                }else if (reason.equals("")) {
                    join_reason.setError("Please enter Reason");
                    join_reason.requestFocus();
                }else {
                    joinUsPresenter.requestJoinUs(sharedPrefs.getAccessToken(),mobile1,
                                                    email1,reason);
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    private void showFeedbackDialogBox() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.feedback_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText feedbackEdittext=(EditText)dialogView.findViewById(R.id.feedback_edittext);
        progressBar = (ProgressBar)dialogView.findViewById(R.id.join_us_bar);
        dialogBuilder.setTitle(R.string.feedback);
        dialogBuilder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String feedback=feedbackEdittext.getText().toString();
				feedbackPresenter.sendFeedback(sharedPrefs.getAccessToken(),feedback);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                dialog.dismiss();

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();


    }
    @Override
    public void showProgressBar(boolean show) {

        if(show){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showFeedbackDialog(String message) {
        final AlertDialog ad = new AlertDialog.Builder(context).create();
        ad.setCancelable(false);
        ad.setTitle(getString(R.string.feedback));
        ad.setMessage(message);
        ad.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ad.cancel();

            }
        });
        ad.show();
    }

    @Override
    public void showDialog(String message) {
        final AlertDialog ad = new AlertDialog.Builder(context).create();
        ad.setCancelable(false);
        ad.setTitle(R.string.join_us);
        ad.setMessage(message);
        ad.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ad.cancel();


            }
        });
        ad.show();
    }
}