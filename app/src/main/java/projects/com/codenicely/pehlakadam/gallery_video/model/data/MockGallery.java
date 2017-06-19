package projects.com.codenicely.pehlakadam.gallery_video.model.data;


import java.util.ArrayList;
import java.util.List;

import projects.com.codenicely.pehlakadam.gallery_video.GalleryCallback;
import projects.com.codenicely.pehlakadam.gallery_video.model.GalleryProvider;

/**
 * Created by iket on 25/2/17.
 */

public class MockGallery implements GalleryProvider {



    @Override
    public void getImageUrls(GalleryCallback galleryCallback) {
        List <ContentDetails> list_content=new ArrayList<>();
        ContentDetails contentDetails=new ContentDetails("http://rameswaramtourism.com/wp-content/uploads/2015/11/clean-india-campaign-rameswaram.jpg",1,"");
        list_content.add(contentDetails);
        list_content.add(contentDetails);
        contentDetails=new ContentDetails("http://rameswaramtourism.com/wp-content/uploads/2015/11/clean-india-campaign-rameswaram.jpg",0,"http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
        list_content.add(contentDetails);
        GalleryData galleryData=new GalleryData(true,"",list_content);
        galleryCallback.onSuccess(galleryData);
    }

    @Override
    public void onDestroy() {

    }
}
