package projects.com.codenicely.pehlakadam.stories.model;

import projects.com.codenicely.pehlakadam.stories.StoriesCallBack;

/**
 * Created by aman on 16/6/17.
 */

public interface StoriesProvider {

    void requestStories(String access_token, StoriesCallBack storiesCallBack);
}
