package projects.com.codenicely.pehlakadam.stories.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.helper.SharedPrefs;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;
import projects.com.codenicely.pehlakadam.stories.model.MockStoriesProvider;
import projects.com.codenicely.pehlakadam.stories.model.RetrofitStoriesProvider;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesLikeShareData;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesListDetails;
import projects.com.codenicely.pehlakadam.stories.presenter.StoriesPresenter;
import projects.com.codenicely.pehlakadam.stories.presenter.StoriesPresenterImpl;

/**
 * Created by aman on 16/6/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private List<StoriesListDetails> storiesListDetailses=new ArrayList<>();
    private StoriesFragment storiesFragment;
    private SharedPrefs sharedPreferences;
    private StoriesPresenter storiesPresenter;
    private StoriesLikeShareData storiesLikeShareData;



    public RecyclerAdapter(Context context,  StoriesFragment storiesFragment) {
        this.context = context;
        this.storiesFragment = storiesFragment;
        this.layoutInflater = LayoutInflater.from(context);
        this.imageLoader =new GlideImageLoader(context);
        this.sharedPreferences = new SharedPrefs(context);
//        this.storiesPresenter= new StoriesPresenterImpl(storiesFragment,new RetrofitStoriesProvider());
        this.storiesPresenter= new StoriesPresenterImpl(storiesFragment,new MockStoriesProvider());

    }

    void updateData(StoriesLikeShareData storiesLikeShareData){
        this.storiesLikeShareData = storiesLikeShareData;
    }

    void setData(List<StoriesListDetails> storiesListDetailses){
        this.storiesListDetailses=storiesListDetailses;
    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.item_recycler_post,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.MyViewHolder holder, final int position) {

        final StoriesListDetails storiesListDetails = storiesListDetailses.get(position);
        holder.bar_card_post.setVisibility(View.GONE);
        if (storiesListDetails.getUser_image() != null) {

            holder.profileImage.setVisibility(View.VISIBLE);
            imageLoader.loadImage(storiesListDetails.getImage(), holder.image_post, holder.bar_user_image);
        } else {
            holder.profileImage.setImageResource(R.drawable.ic_profile);
        }

        holder.user_name.setText(storiesListDetails.getUser_name());
        String instance=storiesListDetails.getDate().toString() +" at "+
                        storiesListDetails.getTime().toString();
        holder.date_post.setText(instance);
        holder.desc_post.setText(storiesListDetails.getDescription());
        if (storiesListDetails.getImage() != null) {

            holder.layout_image_post.setVisibility(View.VISIBLE);
            imageLoader.loadImage(storiesListDetails.getImage(), holder.image_post, holder.bar_image_post);
        } else {
            holder.layout_image_post.setVisibility(View.GONE);
        }
        if(storiesListDetails.isLiked())
        {
            holder.button_like.setImageResource(R.drawable.ic_liked);
        }
        else{
            holder.button_like.setImageResource(R.drawable.ic_like);
        }

        if(storiesListDetails.isShared())
        {
            holder.button_share.setImageResource(R.drawable.ic_shared);
        }
        else{
            holder.button_share.setImageResource(R.drawable.ic_share);
        }
        Log.d("RecyclerAdapter",holder.likesPost.toString());
        holder.likesPost.setText(storiesListDetails.getLikes()+" ");
        holder.shares_post.setText(storiesListDetails.getShares()+" ");
        holder.button_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(storiesListDetails.isLiked())
                {
                    holder.button_like.setImageResource(R.drawable.ic_like);
                }
                else{
                    holder.button_like.setImageResource(R.drawable.ic_liked);
                }
                //// TODO: 17/6/17 Like Presenter Call
                storiesPresenter.requestLikeShare(sharedPreferences.getAccessToken(),
                        storiesListDetails.getStory_id(),position,0);
                if (storiesLikeShareData != null){
                    storiesListDetailses.get(position).setLiked(storiesLikeShareData.isLiked());
                    storiesListDetailses.get(position).setLikes(storiesLikeShareData.getLikes());
                }

                notifyItemChanged(position);
            }
        });
        holder.button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(storiesListDetails.isShared())
                {
                    holder.button_share.setImageResource(R.drawable.ic_share);
                }
                else{
                    holder.button_share.setImageResource(R.drawable.ic_shared);
                }
                //// TODO: 17/6/17 Share Presenter Call
                storiesPresenter.requestLikeShare(sharedPreferences.getAccessToken(),
                        storiesListDetails.getStory_id(),position,1);
                if (storiesLikeShareData != null){
                    storiesListDetailses.get(position).setShared(storiesLikeShareData.isShared());
                    storiesListDetailses.get(position).setShares(storiesLikeShareData.getShares());
                }
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return storiesListDetailses.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_image)
        ImageView profileImage;
        @BindView(R.id.bar_user_image)
        ProgressBar bar_user_image;
        @BindView(R.id.name_post)
        TextView user_name;
        @BindView(R.id.date_post)
        TextView date_post;
        @BindView(R.id.desc_post)
        TextView desc_post;
        @BindView(R.id.layout_image_post)
        RelativeLayout layout_image_post;
        @BindView(R.id.image_post)
        ImageView image_post;
        @BindView(R.id.bar_image_post)
        ProgressBar bar_image_post;
        @BindView(R.id.likes_post)
        TextView likesPost;
        @BindView(R.id.shares_post)
        TextView shares_post;
        @BindView(R.id.button_like)
        ImageView button_like;
        @BindView(R.id.button_share)
        ImageView button_share;

        @BindView(R.id.bar_card_post)
        ProgressBar bar_card_post;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
