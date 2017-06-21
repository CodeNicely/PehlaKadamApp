package projects.com.codenicely.pehlakadam.developers.presenter;


import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.developers.DevelopersCallback;
import projects.com.codenicely.pehlakadam.developers.model.DeveloperProvider;
import projects.com.codenicely.pehlakadam.developers.model.data.DeveloperData;
import projects.com.codenicely.pehlakadam.developers.view.DeveloperView;
import projects.com.codenicely.pehlakadam.helper.MyApplication;

/**
 * Created by meghal on 17/10/16.
 */

public class DevelopersPresenterImpl implements DevelopersPresenter {

    private DeveloperView developerView;
    private DeveloperProvider developerProvider;

    public DevelopersPresenterImpl(DeveloperView developerView, DeveloperProvider developerProvider) {
        this.developerView = developerView;
        this.developerProvider = developerProvider;
    }

    @Override
    public void requestDevelopersData() {

        developerView.showLoading(true);
        developerProvider.requestDevelopersData(new DevelopersCallback() {
            @Override
            public void onSuccess(DeveloperData developerData) {

                developerView.showLoading(false);
                if (developerData.isSuccess()) {
                    developerView.setData(developerData.getDeveloper_data());
                } else {
                    developerView.showMessage(developerData.getMessage());
                }

            }

            @Override
            public void onFailed() {

                developerView.showLoading(false);
                developerView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
            }
        });


    }

    @Override
    public void onDestroy() {
        developerProvider.onDestroy();
    }
}
