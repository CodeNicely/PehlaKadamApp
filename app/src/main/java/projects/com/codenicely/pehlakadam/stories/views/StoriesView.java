package projects.com.codenicely.pehlakadam.stories.views;

import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesLikeShareData;

/**
 * Created by aman on 16/6/17.
 */

public interface StoriesView {

    void showProgressBar(boolean show);
    void setListData(StoriesData storiesData);
    void showMessage(String error);
    void disableButton(boolean enable);
    void showDialogLoader(boolean show);
    void whatsappShare(String text);
    boolean checkPermissionForCamera();

    /**
     * This method is for checking gallery permission.
     * Applicable only for devices with api 23 or more.
     *
     * @return
     */
    boolean checkPermissionForGallery();

    /**
     * This function is for requesting camera permission if user does'nt have taken permission
     * previously.
     *
     * @return
     */
    boolean requestCameraPermission();

    /**
     * This function is for requesting gallery permission if user does'nt have taken permission
     * previously.
     *
     * @return
     */
    boolean requestGalleryPermission();


    /**
     * This method is called when user chooses to open camera.
     */
    void showCamera();

    /**
     * This method is called when user chooses to open gallery.
     */

    void showGallery();

    void fileFromPath(String filePath);
    void updateItemData(StoriesLikeShareData storiesLikeShareData);

}
