package projects.com.codenicely.pehlakadam.about_us.presenter;


import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.about_us.AboutUsCallBack;
import projects.com.codenicely.pehlakadam.about_us.data.AboutUsData;
import projects.com.codenicely.pehlakadam.about_us.model.AboutUsProvider;
import projects.com.codenicely.pehlakadam.about_us.view.AboutUsView;
import projects.com.codenicely.pehlakadam.helper.MyApplication;

/**
 * Created by meghal on 13/10/16.
 */

public class AboutUsPresenterImpl implements AboutUsPresenter {


    private AboutUsView aboutUsView;
    private AboutUsProvider aboutUsProvider;

    public AboutUsPresenterImpl(AboutUsView aboutUsView, AboutUsProvider aboutUsProvider) {
        this.aboutUsView = aboutUsView;
        this.aboutUsProvider = aboutUsProvider;
    }

    @Override
    public void requestAboutUs(int lang_type) {

        aboutUsView.showLoader(true);
        aboutUsProvider.requestAboutUs(lang_type,new AboutUsCallBack() {
            @Override
            public void onSuccess(AboutUsData aboutUsData) {

                aboutUsView.showLoader(false);
                if (aboutUsData.isSuccess()) {

                    aboutUsView.setData(aboutUsData);

                } else {
                    aboutUsView.showMessage(aboutUsData.getMessage());
                }
            }

            @Override
            public void onFailure() {

                aboutUsView.showLoader(false);
//                aboutUsView.showDialog(MyApplication.getContext().getResources().getString(R.string.failure_message));

            }
        });

    }

    @Override
    public void onDestroy() {
        aboutUsProvider.onDestroy();
    }
}
