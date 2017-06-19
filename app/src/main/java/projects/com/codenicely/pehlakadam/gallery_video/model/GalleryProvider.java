package projects.com.codenicely.pehlakadam.gallery_video.model;


import projects.com.codenicely.pehlakadam.gallery_video.GalleryCallback;

/**
 * Created by meghal on 13/10/16.
 */

public interface GalleryProvider {

    void getImageUrls(GalleryCallback galleryCallback);
    void onDestroy();

}
