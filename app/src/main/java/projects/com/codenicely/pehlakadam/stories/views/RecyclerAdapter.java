package projects.com.codenicely.pehlakadam.stories.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesListDetails;

/**
 * Created by aman on 16/6/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private List<StoriesListDetails> storiesListDetailses=new ArrayList<>();
    private StoriesFragment storiesFragment;


    public RecyclerAdapter(Context context,  StoriesFragment storiesFragment) {
        this.context = context;
        this.storiesFragment = storiesFragment;
        this.layoutInflater = LayoutInflater.from(context);
        this.imageLoader =new GlideImageLoader(context);
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
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {
        StoriesListDetails storiesListDetails = storiesListDetailses.get(position);

        if (storiesListDetails.getUser_image() != null) {

            holder.profileImage.setVisibility(View.VISIBLE);
            imageLoader.loadImage(storiesListDetails.getImage(), holder.image_post, holder.bar_image_post);
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
        holder.likes_post.setText(storiesListDetails.getLikes());
        holder.shares_post.setText(storiesListDetails.getShares());
        holder.button_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo : Button Click
            }
        });
        holder.button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo : Button Click
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
        TextView likes_post;
        @BindView(R.id.shares_post)
        TextView shares_post;
        @BindView(R.id.button_like)
        Button button_like;
        @BindView(R.id.button_share)
        Button button_share;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
