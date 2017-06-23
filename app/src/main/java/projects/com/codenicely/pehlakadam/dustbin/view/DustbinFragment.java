package projects.com.codenicely.pehlakadam.dustbin.view;

import android.content.Context;

import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.dustbin.data.DustbinData;
import projects.com.codenicely.pehlakadam.dustbin.data.DustbinDetails;
import projects.com.codenicely.pehlakadam.dustbin.model.RetrofitDustbinProvider;
import projects.com.codenicely.pehlakadam.dustbin.presenter.DustbinPresenter;
import projects.com.codenicely.pehlakadam.dustbin.presenter.DustbinPresenterImpl;
import projects.com.codenicely.pehlakadam.helper.OnMapAndViewReadyListener;
import projects.com.codenicely.pehlakadam.helper.Toaster;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DustbinFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DustbinFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DustbinFragment extends Fragment implements
	 GoogleMap.OnMarkerClickListener,

	 OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener,DustbinView,LocationListener,
			 GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private List<DustbinDetails> dustbinDetailsList = new ArrayList<>();
	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	private GoogleMap mMap;
	private GoogleApiClient mGoogleApiClient;
	private LocationRequest mLocationRequest;
	private double latitude;
	private double longitude;
	private Toaster toaster;
	private Context context;

	//Define a request code to send to Google Play services
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

	private OnFragmentInteractionListener mListener;


	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment DustbinFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static DustbinFragment newInstance(String param1, String param2) {
		DustbinFragment fragment = new DustbinFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public DustbinFragment() {
		// Required empty public constructor
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
		Log.d("DUSTBINONC---","qwertyuiop");
		View v =inflater.inflate(R.layout.fragment_dustbin, container, false);
		context=getContext();
		toaster= new Toaster(context);

		mGoogleApiClient = new GoogleApiClient.Builder(getContext())
								   // The next two lines tell the new client that “this” current class will handle connection stuff
								   .addConnectionCallbacks(this)
								   .addOnConnectionFailedListener(this)
								   //fourth line adds the LocationServices API endpoint from GooglePlayServices
								   .addApi(LocationServices.API)
								   .build();

		// Create the LocationRequest object

		mLocationRequest = LocationRequest.create()
								   .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
								   .setInterval(10 * 1000)        // 10 seconds, in milliseconds
								   .setFastestInterval(1 * 1000); // 1 second, in milliseconds

		mGoogleApiClient.connect();


		LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

		final DustbinPresenter dustbinPresenter =new DustbinPresenterImpl(this,new RetrofitDustbinProvider());

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				dustbinPresenter.getDustbinData(latitude,longitude);

			}
		},500);




		// Inflate the layout for this fragment
		return v;
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
	public boolean onMarkerClick(Marker marker)
	{
		return false;
	}


	@Override
	public void onMapReady(GoogleMap map) {
		mMap = map;

		// Hide the zoom controls as the button panel will cover it.
		mMap.getUiSettings().setZoomControlsEnabled(false);

		// Add lots of markers to the map.
		addMarkersToMap();

		// Setting an info window adapter allows us to change the both the contents and look of the
		// info window.
//		mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

		// Set listeners for marker events.  See the bottom of this class for their behavior.
		mMap.setOnMarkerClickListener(this);

		// Override the default content description on the view, for accessibility mode.
		// Ideally this string would be localised.
		mMap.setContentDescription("Map with lots of markers.");


//		try {
			LatLngBounds bounds = new LatLngBounds.Builder()
					.include(new LatLng(dustbinDetailsList.get(0).getLatitude(), dustbinDetailsList.get(0).getLongitude()))
					.build();
			mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
//		}catch (IndexOutOfBoundsException e)
//		{
//			toaster.showMessage("Null List");
//		}
	}

	private void addMarkersToMap() {
		for (int i=0;i<dustbinDetailsList.size();i++) {
			mMap.addMarker(new MarkerOptions()
		   .position(new LatLng(dustbinDetailsList.get(i).getLatitude(), dustbinDetailsList.get(i).getLongitude()))
		   .title(dustbinDetailsList.get(i).getName())
		   .snippet("Distance:"+dustbinDetailsList.get(i).getDistance()+"km"));
		}
	}

	@Override
	public void showLoader(boolean show) {
		if (show){

		}else {

		}
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void setData(DustbinData dustbinData) {
		dustbinDetailsList= dustbinData.getDustbin_list();
		Log.d("DUSTBIN--",dustbinDetailsList.size()+"--"+dustbinData.getDustbin_list().size());
		SupportMapFragment mapFragment =
				(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
		new OnMapAndViewReadyListener(mapFragment, this);

	}

	@Override
	public void onConnected(@Nullable Bundle bundle) {

		if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

		if (location == null) {
			LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

		} else {
			//If everything went fine lets get latitude and longitude
			latitude = location.getLatitude();
			longitude = location.getLongitude();

		//	Toast.makeText(getContext(),String.valueOf(latitude),Toast.LENGTH_SHORT).show();

//			latitudeLongitude.setText("Current Location - " + String.valueOf(latitude)
//											  + " , " + longitude);

		}

	}

	@Override
	public void onDestroyView() {

		super.onDestroyView();
	}

	@Override
	public void onDestroy() {

		super.onDestroy();

		getChildFragmentManager().beginTransaction().remove(DustbinFragment.this).commit();
	}

	@Override
	public void onConnectionSuspended(int i) {

	}


	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
     /*
             * Google Play services can resolve some errors it detects.
             * If the error has a resolution, try sending an Intent to
             * start a Google Play services activity that can resolve
             * error.
             */
		if (connectionResult.hasResolution()) {
			try {
				// Start an Activity that tries to resolve the error
				connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
                    /*
                     * Thrown if Google Play services canceled the original
                     * PendingIntent
                     */
			} catch (IntentSender.SendIntentException e) {
				// Log the error
				e.printStackTrace();
			}
		} else {
                /*
                 * If no resolution is available, display a dialog to the
                 * user with the error.
                 */
			Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
		}


	}

	@Override
	public void onLocationChanged(Location location) {
		latitude = location.getLatitude();
		longitude = location.getLongitude();


//		latitudeLongitude.setText("Current Location - " + String.valueOf(latitude)
//										  + " , " + longitude);

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
