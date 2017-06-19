package projects.com.codenicely.pehlakadam.stories;

import projects.com.codenicely.pehlakadam.stories.model.data.StoriesLikeShareData;

/**
 * Created by aman on 17/6/17.
 */

public interface StoriesLikeShareCallBack {

    void onSuccess(StoriesLikeShareData storiesLikeShareData);
    void onFailure(String error);
}
