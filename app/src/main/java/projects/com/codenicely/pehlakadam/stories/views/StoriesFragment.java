package projects.com.codenicely.pehlakadam.stories.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import projects.com.codenicely.pehlakadam.stories.model.MockStoriesProvider;
import projects.com.codenicely.pehlakadam.stories.model.RetrofitStoriesProvider;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;
import projects.com.codenicely.pehlakadam.stories.presenter.StoriesPresenter;
import projects.com.codenicely.pehlakadam.stories.presenter.StoriesPresenterImpl;

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

    @BindView(R.id.button_post)
    Button button_post;

    @BindView(R.id.recycler_post)
    RecyclerView recycler_post;

    @BindView(R.id.progress_post)
    ProgressBar progress_post;

    private OnFragmentInteractionListener mListener;

    public StoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        initialize();

        if (sharedPrefs.isLoggedIn()){
            cardView.setEnabled(true);
        }
        else {
            Log.d("StoriesFragment","Checking Login");
            cardView.setEnabled(false);
        }

        storiesPresenter.requestStories(sharedPrefs.getAccessToken());
        imageLoader.loadImage(sharedPrefs.getProfileImage(), profile_image, bar_profile_image);
        Log.d("StoriesFragment","Below ImageLoader");
        icon_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// TODO: 17/6/17 Camera Permission or Gallery Permission
            }
        });
        button_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo : Add Post Module
            }
        });
        return view;
    }

    void initialize(){

        sharedPrefs=new SharedPrefs(getContext());
//        storiesPresenter = new StoriesPresenterImpl(this,new RetrofitStoriesProvider());
        storiesPresenter = new StoriesPresenterImpl(this,new MockStoriesProvider());
        imageLoader =new GlideImageLoader(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerAdapter = new RecyclerAdapter(getContext(),this);
        recycler_post.setLayoutManager(linearLayoutManager);
        recycler_post.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(getContext(),this);
        recycler_post.setAdapter(recyclerAdapter);
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
            Log.d("Stories","progressBar Gone");
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
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
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
