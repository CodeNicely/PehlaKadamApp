package projects.com.codenicely.pehlakadam.stories;

import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;

/**
 * Created by aman on 16/6/17.
 */

public interface StoriesCallBack {

    void onSuccess(StoriesData storiesData);
    void onFailure(String error);
}
