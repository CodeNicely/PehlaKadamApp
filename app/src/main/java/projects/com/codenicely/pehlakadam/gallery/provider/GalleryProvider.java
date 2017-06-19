package projects.com.codenicely.pehlakadam.gallery.provider;


import projects.com.codenicely.pehlakadam.gallery.OnGalleryApiResponse;

/**
 * Created by meghalagrawal on 03/06/17.
 */

public interface GalleryProvider {

    void getImages(int language_type, OnGalleryApiResponse onGalleryApiResponse);
    void onDestroy();
}
