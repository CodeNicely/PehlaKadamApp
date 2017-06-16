package projects.com.codenicely.pehlakadam.stories.views;

import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;

/**
 * Created by aman on 16/6/17.
 */

public interface StoriesView {

    void showProgressBar(boolean show);
    void setData(StoriesData storiesData);
    void showMessage(String error);

}
