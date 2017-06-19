package projects.com.codenicely.pehlakadam.gallery.view;


import java.util.List;

import projects.com.codenicely.pehlakadam.gallery.model.ImageData;

/**
 * Created by meghalagrawal on 03/06/17.
 */

public interface GalleryView {


    void showLoader(boolean show);
    void onGalleryData(List<ImageData> imageDataList);
    void showMessage(String message);
}
