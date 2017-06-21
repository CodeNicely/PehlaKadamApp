package projects.com.codenicely.pehlakadam.home.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.about_us.view.AboutUsFragment;
import projects.com.codenicely.pehlakadam.contact_us.view.ContactUsFragment;
import projects.com.codenicely.pehlakadam.gallery.view.GalleryFragment;
import projects.com.codenicely.pehlakadam.gallery_video.model.data.ContentDetails;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.image_viewer.ImageViewerActivity;
import projects.com.codenicely.pehlakadam.join_us.model.RetrofitJoinUsProvider;
import projects.com.codenicely.pehlakadam.join_us.presenter.JoinUsPresenter;
import projects.com.codenicely.pehlakadam.join_us.presenter.JoinUsPresenterImpl;
import projects.com.codenicely.pehlakadam.join_us.view.JoinUsView;
import projects.com.codenicely.pehlakadam.stories.views.StoriesFragment;
import projects.com.codenicely.pehlakadam.video_player.VideoPlayer;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, JoinUsView{

    private SharedPrefs sharedPrefs;
    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPagerAdapter viewPagerAdapter;
    private Context context;
    private ProgressBar progressBar;
    private JoinUsPresenter joinUsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        context=this;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        joinUsPresenter= new JoinUsPresenterImpl(context,this,new RetrofitJoinUsProvider());
        Log.d("HomeActivity----","1"+joinUsPresenter.toString());
        setFragment(new HomeFragment(),"Pehla Kadam");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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


        } else if (id == R.id.nav_feedback) {


        } else if (id == R.id.nav_join_us) {
            showDialogBox();

        } else if (id == R.id.nav_about_us) {
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            addFragment(aboutUsFragment,"About Us");

        } else if (id == R.id.nav_contact_us) {

            ContactUsFragment contactUsFragment = new ContactUsFragment();
            setFragment(contactUsFragment,"Contact Us");
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
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.join_us_dialog, null);
        dialogBuilder.setView(dialogView);
        Log.d("HomeActivity----","2"+joinUsPresenter.toString());
        final EditText join_reason=(EditText)dialogView.findViewById(R.id.join_reason) ;
        progressBar = (ProgressBar)dialogView.findViewById(R.id.join_us_bar);
        dialogBuilder.setTitle(R.string.join_us);
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                String desc=join_reason.getText().toString();
                Log.d("HomeActivity----","3"+joinUsPresenter.toString());

                joinUsPresenter.requestJoinUs(sharedPrefs.getAccessToken(),desc);
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
    public void showDialog(String message) {
        final AlertDialog ad = new AlertDialog.Builder(context).create();
        ad.setCancelable(false);
        ad.setTitle(R.string.join_us);
        ad.setMessage(message);
        ad.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ad.cancel();

            }
        });
        ad.show();
    }
}