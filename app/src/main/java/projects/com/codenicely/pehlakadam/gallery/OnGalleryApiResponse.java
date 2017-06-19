package projects.com.codenicely.pehlakadam.gallery;


import projects.com.codenicely.pehlakadam.gallery.model.GalleryData;

/**
 * Created by meghalagrawal on 03/06/17.
 */

public interface OnGalleryApiResponse {

    void onSuccess(GalleryData galleryData);

    void onFailed(String message);

}
