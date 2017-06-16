package projects.com.codenicely.pehlakadam.stories.presenter;

import projects.com.codenicely.pehlakadam.stories.StoriesCallBack;
import projects.com.codenicely.pehlakadam.stories.model.StoriesProvider;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;
import projects.com.codenicely.pehlakadam.stories.views.StoriesView;

/**
 * Created by aman on 16/6/17.
 */

public class StoriesPresenterImpl implements StoriesPresenter {

    private StoriesView storiesView;
    private StoriesProvider storiesProvider;

    public StoriesPresenterImpl(StoriesView storiesView, StoriesProvider storiesProvider) {
        this.storiesView = storiesView;
        this.storiesProvider = storiesProvider;
    }

    @Override
    public void requestStories(String access_token) {
        storiesView.showProgressBar(true);
        storiesProvider.requestStories(access_token, new StoriesCallBack() {
            @Override
            public void onSuccess(StoriesData storiesData) {
                if(storiesData.isSuccess())
                {
                    storiesView.showProgressBar(false);
                    storiesView.setData(storiesData);

                }
                else {
                    storiesView.showProgressBar(false);
                    storiesView.showMessage(storiesData.getMessage());

                }

            }

            @Override
            public void onFailure(String error) {
                storiesView.showProgressBar(false);
                storiesView.showMessage("No Internet Connection");
            }
        });

    }
}
