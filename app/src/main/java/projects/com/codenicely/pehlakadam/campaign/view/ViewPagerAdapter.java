package projects.com.codenicely.pehlakadam.campaign.view;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.campaign.model.data.ImageListDetails;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;

/**
 * Created by aman on 21/6/17.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private CampaignFragment campaignFragment;
    private LayoutInflater layoutInflater;
    private List<ImageListDetails> imageListDetailses=new ArrayList<>();
    private ImageView imageView;
    private ProgressBar progressBar;
    private ImageLoader imageLoader;

    public ViewPagerAdapter(Context context, CampaignFragment campaignFragment) {
        this.context = context;
        this.campaignFragment = campaignFragment;
        imageLoader = new GlideImageLoader(context);
    }

    public void setPagerData(List<ImageListDetails> imageListDetailses){
        this.imageListDetailses=imageListDetailses;
        Log.d("CampaignViewPager","--"+" "+ imageListDetailses.size());
    }

    @Override
    public Object instantiateItem(ViewGroup container,final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.item_viewpager_campaign,container,false);
        container.addView(view);
        imageView= (ImageView)view.findViewById(R.id.image_pager_campaign);
        progressBar= (ProgressBar)view.findViewById(R.id.img_bar_campaign);
        ImageListDetails imageListData= imageListDetailses.get(position);
        Log.d("CampaignViewPager",position+" "+ imageListData.getUrl());
        if(imageListData.getUrl().equals("")){
            imageView.setImageResource(R.drawable.pk_icon10_text_green);
            progressBar.setVisibility(View.GONE);
        }else{
            imageLoader.loadImage(imageListData.getUrl(), imageView, progressBar);
        }
        return view;
    }
    @Override
    public int getCount() {
        try {
            return imageListDetailses.size();
        }catch (NullPointerException e){
            return 0;
        }
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

    @Override
    public float getPageWidth(int position) {
        super.getPageWidth(position);
        return 0.4f;
    }
}
