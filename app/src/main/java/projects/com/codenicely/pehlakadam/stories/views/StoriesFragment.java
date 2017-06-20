package projects.com.codenicely.pehlakadam.stories.views;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;
import projects.com.codenicely.pehlakadam.stories.model.MockStoriesProvider;
import projects.com.codenicely.pehlakadam.stories.model.RetrofitStoriesProvider;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesLikeShareData;
import projects.com.codenicely.pehlakadam.stories.presenter.StoriesPresenter;
import projects.com.codenicely.pehlakadam.stories.presenter.StoriesPresenterImpl;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoriesFragment extends Fragment implements StoriesView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SharedPrefs sharedPrefs;
    private StoriesPresenter storiesPresenter;
    private RecyclerAdapter recyclerAdapter;
    private ImageLoader imageLoader;
    private ProgressDialog progressDialog;
    private Context context;
    private Uri imageUri = null;
    private boolean CAMERA_REQUEST = false;
    private boolean GALLERY_REQUEST = false;
    private static final int CAMERA_REQUEST_ID = 100;
    private final int GALLERY_REQUEST_ID = 1;
    private File image = null;

    @BindView(R.id.card_post)
    CardView cardView;

    @BindView(R.id.profile_image)
    ImageView profile_image;

    @BindView(R.id.bar_profile_image)
    ProgressBar bar_profile_image;

    @BindView(R.id.text_post)
    TextView text_post;

    @BindView(R.id.icon_camera)
    ImageView icon_camera;

    @BindView(R.id.icon_gallery)
    ImageView icon_gallery;

    @BindView(R.id.button_post)
    Button button_post;

    @BindView(R.id.recycler_post)
    RecyclerView recycler_post;

    @BindView(R.id.progress_post)
    ProgressBar progress_post;

    @BindView(R.id.imageView)
    ImageView imageView;

    private OnFragmentInteractionListener mListener;

    public StoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters
     * @return A new instance of fragment StoriesFragment.
     */
    public static StoriesFragment newInstance() {
        StoriesFragment fragment = new StoriesFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_stories, container, false);
        ButterKnife.bind(this,view);
        context = getContext();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait . . .");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        initialize();

        if (sharedPrefs.isLoggedIn()){
            cardView.setEnabled(true);
        }
        else {

            cardView.setEnabled(false);
        }
        storiesPresenter.requestStories(sharedPrefs.getAccessToken());
        if ( sharedPrefs.getProfileImage().equals("profile_image" )|| sharedPrefs.getProfileImage().equals("") ) {

            profile_image.setImageResource(R.drawable.ic_profile);
            bar_profile_image.setVisibility(View.INVISIBLE);
        } else {
            imageLoader.loadImage(sharedPrefs.getProfileImage(), profile_image, bar_profile_image);
        }

        icon_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storiesPresenter.openCamera();
            }
        });

        icon_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storiesPresenter.openGallery();
            }
        });

        button_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo : Add Post Module
                String desc =text_post.getText().toString();
                hideKeyboard();
                storiesPresenter.addStories(sharedPrefs.getAccessToken(),"Title",desc,imageUri);
            }
        });
        hideKeyboard();
        return view;
    }

    void initialize(){
        sharedPrefs=new SharedPrefs(context);
//        storiesPresenter = new StoriesPresenterImpl(this,new RetrofitStoriesProvider(context));
        storiesPresenter = new StoriesPresenterImpl(this,new MockStoriesProvider());
        imageLoader =new GlideImageLoader(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerAdapter = new RecyclerAdapter(context,this);
        recycler_post.setLayoutManager(linearLayoutManager);
        recycler_post.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(context,this);
        recycler_post.setAdapter(recyclerAdapter);
        Dexter.initialize(context);

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
    public void showProgressBar(boolean show) {
        if(show)
        {
            progress_post.setVisibility(View.VISIBLE);
        }
        else
        {
            progress_post.setVisibility(View.GONE);
        }
    }

    @Override
    public void setListData(StoriesData storiesData) {
        recyclerAdapter.setData(storiesData.getStories_list());
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String error) {
        Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkPermissionForCamera() {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }

    @Override
    public boolean checkPermissionForGallery() {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;

    }

    @Override
    public boolean requestCameraPermission() {
        Dexter.checkPermissions(new MultiplePermissionsListener() {

            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {


                if (multiplePermissionsReport.areAllPermissionsGranted()) {

                    CAMERA_REQUEST = true;
                } else {
                    CAMERA_REQUEST = false;
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);


        return CAMERA_REQUEST;
    }

    @Override
    public boolean requestGalleryPermission() {
        Dexter.checkPermission(new PermissionListener() {

            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */

                GALLERY_REQUEST = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */

                GALLERY_REQUEST = false;
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
        }, Manifest.permission.READ_EXTERNAL_STORAGE);


        return GALLERY_REQUEST;
    }

    @Override
    public void showCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_REQUEST_ID);
        }
    }

    @Override
    public void showGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_ID);

    }

    @Override
    public void fileFromPath(String filePath) {
        image = new File(filePath);
    }

    @Override
    public void updateItemData(StoriesLikeShareData storiesLikeShareData) {
        recyclerAdapter.updateData(storiesLikeShareData);
    }


    @Override
    public void showDialogLoader(boolean show) {
        if (show) {
            progressDialog.show();
        } else {
            progressDialog.hide();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_ID && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            if (imageUri != null) {
                Glide.with(this).load(imageUri).fitCenter().crossFade().into(imageView);
            }

        } else if (requestCode == CAMERA_REQUEST_ID && resultCode == RESULT_OK) {
            imageUri = Uri.fromFile(image);
            if (imageUri != null) {
                Glide.with(this).load(imageUri).fitCenter().crossFade().into(imageView);
            }
        }
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

        void onFragmentInteraction(Uri uri);
    }
}
