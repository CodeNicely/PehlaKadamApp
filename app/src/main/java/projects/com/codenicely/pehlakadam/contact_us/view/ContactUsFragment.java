package projects.com.codenicely.pehlakadam.contact_us.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.contact_us.model.MockProvider;
import projects.com.codenicely.pehlakadam.contact_us.model.RetrofitContactUsProvider;
import projects.com.codenicely.pehlakadam.contact_us.model.data.ContactUsData;
import projects.com.codenicely.pehlakadam.contact_us.presenter.ContactUsPresenter;
import projects.com.codenicely.pehlakadam.contact_us.presenter.ContactUsPresenterImpl;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;
import projects.com.codenicely.pehlakadam.home.views.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFragment extends Fragment implements ContactUsView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.facebook)
    TextView facebook;
    @BindView(R.id.imageProgressBar)
    ProgressBar imageProgressBar;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.contactUsLayout)
    LinearLayout contactUsLayout;
    @BindView(R.id.emailCard)
    CardView emailCard;
    @BindView(R.id.phoneCard)
    CardView phoneCard;
    @BindView(R.id.locationCard)
    CardView locationCard;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageLoader imageLoader;
    private View snackView;
    private ContactUsPresenter contactUsPresenter;
    private OnFragmentInteractionListener mListener;
    private SharedPrefs sharedPrefs;
    private Context context;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        sharedPrefs = new SharedPrefs(context);
        ((HomeActivity)context).getSupportActionBar().hide();
        toolbar.setNavigationIcon(ContextCompat.getDrawable(context, R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        snackView = getActivity().findViewById(R.id.home_layout);
//        contactUsPresenter = new ContactUsPresenterImpl(this, new RetrofitContactUsProvider());
        contactUsPresenter = new ContactUsPresenterImpl(this, new MockProvider());
        contactUsPresenter.requestContactUs(sharedPrefs.getUserLanguage());
        imageLoader = new GlideImageLoader(context);
        return view;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoader(boolean show) {

        if (show) {

            contactUsLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            contactUsLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(snackView, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void setData(final ContactUsData contactUsData) {

        final String facebookUrl = "https://www.facebook.com/"+ contactUsData.getFacebook();
        email.setText(contactUsData.getEmail());
        mobile.setText(contactUsData.getMobile());
        address.setText(contactUsData.getAddress());
        facebook.setText(facebookUrl);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFacebookIntent(context.getPackageManager(),facebookUrl);
            }
        });

    }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context, String facebookUrl, String facebookPageId) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + facebookUrl;
            } else { //older versions of fb app
                return "fb://page/" + facebookPageId;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return facebookUrl; //normal web url
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        contactUsPresenter.onDestroy();
    }

    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {

                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((HomeActivity)context).getSupportActionBar().show();
        ((HomeActivity)context).getSupportActionBar().setTitle("Home");
    }
}
