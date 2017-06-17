package projects.com.codenicely.pehlakadam.stories.presenter;



/**
 * Created by aman on 16/6/17.
 */

public interface StoriesPresenter {

    void requestStories(String access_token);
    void requestLikeShare(String access_token, int story_id, int button_id);
}
