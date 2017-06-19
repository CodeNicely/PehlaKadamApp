package projects.com.codenicely.pehlakadam.gallery_video.view;


import projects.com.codenicely.pehlakadam.gallery_video.model.data.GalleryData;

/**
 * Created by meghal on 13/10/16.
 */

public interface GalleryView {

    void showLoader(boolean show);
    void showMessage(String message);
    void setData(GalleryData galleryData);
}
