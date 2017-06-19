package projects.com.codenicely.pehlakadam.gallery_video.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;


import java.util.ArrayList;
import java.util.List;

import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.gallery_video.model.data.ContentDetails;
import projects.com.codenicely.pehlakadam.helper.image_loader.GlideImageLoader;
import projects.com.codenicely.pehlakadam.helper.image_loader.ImageLoader;
import projects.com.codenicely.pehlakadam.home.views.HomeActivity;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ImageViewHolder> {


    private Context context;
    private LayoutInflater layoutInflater;
    private List<ContentDetails> contentDetailses=new ArrayList<>();
    private ImageLoader imageLoader;

    public GalleryAdapter(Context context) {
        this.context = context;
        layoutInflater= LayoutInflater.from(context);
        imageLoader=new GlideImageLoader(context);
    }

    @Override
    public GalleryAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_video_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {

        ImageViewHolder imageViewHolder=(ImageViewHolder)holder;
        final int i=contentDetailses.get(position).getType();

        imageLoader.loadImage(contentDetailses.get(position).getImage_url(),holder.imageView,holder.progressBar);

        if(i==0)
            imageLoader.loadImage1("https://image.flaticon.com/icons/png/512/0/375.png",holder.play);
        else holder.play.setVisibility(View.GONE);

        imageViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("res","1");
                if(i==0){
                    ((HomeActivity) context).playVideo(contentDetailses.get(position).getVideo_url());
                }
                else if(i==1)
                {
                    ((HomeActivity)context).showImage(contentDetailses,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return contentDetailses.size();
    }


    public void setImageUrlList(List<ContentDetails> imageUrlList){
        this.contentDetailses=imageUrlList;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;
        ImageView imageView,play;

        public ImageViewHolder(View itemView) {
            super(itemView);
            play=(ImageView)itemView.findViewById(R.id.play);
            progressBar=(ProgressBar)itemView.findViewById(R.id.progressBar_gallery);
            imageView=(ImageView)itemView.findViewById(R.id.image_gallery);

        }
    }

}
