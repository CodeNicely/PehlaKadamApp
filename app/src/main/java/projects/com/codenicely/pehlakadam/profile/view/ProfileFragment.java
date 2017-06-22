package projects.com.codenicely.pehlakadam.profile.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;
import projects.com.codenicely.pehlakadam.profile.data.ProfileData;
import projects.com.codenicely.pehlakadam.profile.model.MockProfileProvider;
import projects.com.codenicely.pehlakadam.profile.presenter.ProfilePresenter;
import projects.com.codenicely.pehlakadam.profile.presenter.ProfilePresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ProfileFragment extends Fragment implements ProfileView {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;
	private Context context;
	private ProfilePresenter profilePresenter;
	private SharedPrefs sharedPrefs;
	@BindView(R.id.progressBar)
	ProgressBar progressBar;
	@BindView(R.id.phone_textview)
	TextView textViewPhone;
	@BindView(R.id.name_textview)
	TextView textViewName;
	@BindView(R.id.ward_textview)
	TextView textViewWard;
	@BindView(R.id.imageView)
	ImageView imageView;
	@BindView(R.id.imageProgressBar)
	ProgressBar imageProgressBar;

	ImageLoader imageLoader;
	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment ProfileFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ProfileFragment newInstance(String param1, String param2) {
		ProfileFragment fragment = new ProfileFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public ProfileFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=getContext();
		imageLoader = new GlideImageLoader(context);

		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view =inflater.inflate(R.layout.fragment_profile, container, false);
		ButterKnife.bind(this,view);
		sharedPrefs = new SharedPrefs(context);
		profilePresenter = new ProfilePresenterImpl(this,new MockProfileProvider());
		profilePresenter.requestProfile(sharedPrefs.getAccessToken(),sharedPrefs.getUserLanguage());
		return view;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
//		if (context instanceof OnFragmentInteractionListener) {
//			mListener = (OnFragmentInteractionListener) context;
//		} else {
//			throw new RuntimeException(context.toString()
//											   + " must implement OnFragmentInteractionListener");
//		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void showProgressBar(boolean show) {
		if (show){
			progressBar.setVisibility(View.VISIBLE);
		}else {
			progressBar.setVisibility(View.GONE);
		}
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void setData(ProfileData profileData) {
		textViewName.setText(profileData.getName());
		textViewPhone.setText(profileData.getMobile());
		textViewWard.setText(profileData.getWard());
		if (!profileData.getImage().equals("")){
			sharedPrefs.setProfileImage(profileData.getImage());
		}

		if ( sharedPrefs.getProfileImage().equals("profile_image" )|| sharedPrefs.getProfileImage().equals("") ) {

			imageView.setImageResource(R.drawable.ic_profile);
			imageProgressBar.setVisibility(View.INVISIBLE);
		} else {
			imageLoader.loadImage(sharedPrefs.getProfileImage(), imageView, imageProgressBar);
		}

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
}