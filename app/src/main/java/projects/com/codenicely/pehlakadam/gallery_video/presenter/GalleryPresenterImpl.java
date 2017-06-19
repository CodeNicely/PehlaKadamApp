package projects.com.codenicely.pehlakadam.gallery_video.presenter;


import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.gallery_video.GalleryCallback;
import projects.com.codenicely.pehlakadam.gallery_video.model.GalleryProvider;
import projects.com.codenicely.pehlakadam.gallery_video.model.data.GalleryData;
import projects.com.codenicely.pehlakadam.gallery_video.view.GalleryView;
import projects.com.codenicely.pehlakadam.helper.MyApplication;

/**
 * Created by meghal on 13/10/16.
 */

public class GalleryPresenterImpl implements GalleryPresenter {

    private GalleryView galleryView;
    private GalleryProvider galleryProvider;

    public GalleryPresenterImpl(GalleryView galleryView, GalleryProvider galleryProvider) {
        this.galleryView = galleryView;
        this.galleryProvider = galleryProvider;
    }

    @Override
    public void getImageUrls() {

        galleryView.showLoader(true);
        galleryProvider.getImageUrls(new GalleryCallback() {
            @Override
            public void onSuccess(GalleryData galleryData) {

                galleryView.showLoader(false);

                if(galleryData.isSuccess())
                    galleryView.setData(galleryData);
                else
                    galleryView.showMessage(galleryData.getMessage());
            }

            @Override
            public void onFailure() {
                galleryView.showLoader(false);
                galleryView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
            }
        });

    }

    @Override
    public void onDestroy() {

        galleryProvider.onDestroy();

    }
}
