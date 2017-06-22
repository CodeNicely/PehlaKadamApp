package projects.com.codenicely.pehlakadam.campaign.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignListDetails;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;


/**
 * Created by aman on 21/6/17.
 */

public class CampaignRecyclerAdapter extends RecyclerView.Adapter<CampaignRecyclerAdapter.MyViewHolder>
{

    private Context context;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private CampaignFragment campaignFragment;
    private List<CampaignListDetails> campaignListDetailsList= new ArrayList<>();
    private CampaignListDetails campaignListData;
    private int campaign_type;//0 = Past & 1 = Upcoming
    private ViewPagerAdapter viewPagerAdapter;


    public CampaignRecyclerAdapter(Context context, CampaignFragment campaignFragment) {
        this.context = context;
        this.campaignFragment = campaignFragment;
        this.layoutInflater = LayoutInflater.from(context);
        this.imageLoader= new GlideImageLoader(context);
        this.viewPagerAdapter = new ViewPagerAdapter(context,campaignFragment);

    }

    void setData(List<CampaignListDetails> campaignListDetailses,int campaign_type){
        this.campaignListDetailsList=campaignListDetailses;
        this.campaign_type=campaign_type;
        Log.d("CampaignRecycler",campaign_type+"campaign_type"+campaignListDetailses.size());
    }

    @Override
    public CampaignRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.item_recycler_campaign,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CampaignRecyclerAdapter.MyViewHolder holder, int position) {
        holder.bar_card.setVisibility(View.VISIBLE);
        campaignListData=campaignListDetailsList.get(position);
        try {
            if (campaignListData.getImage().equals("") || campaignListData.getImage().equals(null)) {
                holder.imageView.setImageResource(R.drawable.ic_campaign);
                holder.progressBar.setVisibility(View.INVISIBLE);
            } else {
                imageLoader.loadImage(campaignListData.getImage(), holder.imageView, holder.progressBar);
            }
        }catch (NullPointerException e){
            holder.imageView.setImageResource(R.drawable.ic_campaign);
            holder.progressBar.setVisibility(View.INVISIBLE);
        }
        holder.name_campaign.setText(campaignListData.getName());
        holder.date_campaign.setText(campaignListData.getDate());
        Log.d("CampaignRecyclerAdapter","--"+campaign_type+" "+ campaignListData.getDescription());
        holder.desc_campaign.setText(campaignListData.getDescription());
        holder.venue_campaign.setText(campaignListData.getVenue());
        holder.bar_card.setVisibility(View.GONE);
        if(campaign_type==0)
        {
            holder.layout_pager_campaign.setVisibility(View.VISIBLE);
            holder.pager_campaign.setAdapter(viewPagerAdapter);
            Log.d("CampaignRecyclerAdapter","--"+campaign_type+" "+
                    campaignListData.getImage_list().size());
            try{
                viewPagerAdapter.setPagerData(campaignListData.getImage_list());
                viewPagerAdapter.notifyDataSetChanged();

            }catch (NullPointerException e)
            {
                Log.d("CampaignRecyclerAdapter","NullPointer");
                e.printStackTrace();
            }

        }
    }

    @Override
    public int getItemCount() {
        return campaignListDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.bar_image)
        ProgressBar progressBar;
        @BindView(R.id.name_campaign)
        TextView name_campaign;
        @BindView(R.id.date_campaign)
        TextView date_campaign;
        @BindView(R.id.venue_campaign)
        TextView venue_campaign;
        @BindView(R.id.desc_campaign)
        TextView desc_campaign;
        @BindView(R.id.layout_pager_campaign)
        LinearLayout layout_pager_campaign;
        @BindView(R.id.pager_campaign)
        ViewPager pager_campaign;
        @BindView(R.id.bar_card)
        ProgressBar bar_card;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
