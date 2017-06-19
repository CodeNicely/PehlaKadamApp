package projects.com.codenicely.pehlakadam.gallery_video;


import projects.com.codenicely.pehlakadam.gallery_video.model.data.GalleryData;

/**
 * Created by meghal on 13/10/16.
 */

public interface GalleryCallback {


    void onSuccess(GalleryData galleryData);
    void onFailure();

}
