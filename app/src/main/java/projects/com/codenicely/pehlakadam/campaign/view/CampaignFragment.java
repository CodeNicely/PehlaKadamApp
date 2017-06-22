package projects.com.codenicely.pehlakadam.campaign.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.karumi.dexter.Dexter;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.campaign.model.MockCampaignProvider;
import projects.com.codenicely.pehlakadam.campaign.model.RetrofitCampaignProvider;
import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignData;
import projects.com.codenicely.pehlakadam.campaign.presenter.CampaignPresenter;
import projects.com.codenicely.pehlakadam.campaign.presenter.CampaignPresenterImpl;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.helper.Toaster;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.stories.views.RecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CampaignFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CampaignFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampaignFragment extends Fragment implements CampaignView {

    private OnFragmentInteractionListener mListener;
    private SharedPrefs sharedPrefs;
    private Context context;
    private CampaignPresenter campaignPresenter;
    private CampaignRecyclerAdapter recyclerAdapter;
    private Toaster toaster;

    @BindView(R.id.button_upcoming)
    Button button_upcoming;
    @BindView(R.id.button_past)
    Button button_past;
    @BindView(R.id.recycler_campaign)
    RecyclerView recycler_campaign;
    @BindView(R.id.progress_campaign)
    ProgressBar progressBar;


    public CampaignFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CampaignFragment.
     */

    public static CampaignFragment newInstance() {
        CampaignFragment fragment = new CampaignFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_campaign, container, false);
        ButterKnife.bind(this,view);
        context=getContext();
        initialize();
        campaignPresenter.requestCampaign(sharedPrefs.getUserLanguage(),1);
        button_past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                campaignPresenter.requestCampaign(sharedPrefs.getUserLanguage(),0);
            }
        });

        button_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                campaignPresenter.requestCampaign(sharedPrefs.getUserLanguage(),1);
            }
        });
        return  view;
    }

    void initialize(){
        sharedPrefs=new SharedPrefs(context);
//        campaignPresenter = new CampaignPresenterImpl(context,this, new RetrofitCampaignProvider()) ;
        campaignPresenter = new CampaignPresenterImpl(context,this,new MockCampaignProvider());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerAdapter = new CampaignRecyclerAdapter(context,this);
        recycler_campaign.setLayoutManager(linearLayoutManager);
        recycler_campaign.setHasFixedSize(true);
        recyclerAdapter = new CampaignRecyclerAdapter(context,this);
        recycler_campaign.setAdapter(recyclerAdapter);
        Dexter.initialize(context);
        toaster = new Toaster(context);

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
            progressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setData(CampaignData campaignData, int campaign_type) {
        recyclerAdapter.setData(campaignData.getPast(),campaign_type);
        recyclerAdapter.notifyDataSetChanged();
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
