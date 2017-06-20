package projects.com.codenicely.pehlakadam.stories.presenter;


import android.net.Uri;

/**
 * Created by aman on 16/6/17.
 */

public interface StoriesPresenter {

    void requestStories(String access_token);
    void requestLikeShare(String access_token, int story_id,int position ,int button_id);
    void openCamera(); //This function is called to open camera for clicking new image
    void openGallery();//This function s called from view if user chooses to select images already present in gallery
    void addStories(String access_token, String title, String description, Uri imageUri);

}

