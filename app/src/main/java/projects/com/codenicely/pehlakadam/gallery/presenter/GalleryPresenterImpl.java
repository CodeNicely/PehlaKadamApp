package projects.com.codenicely.pehlakadam.gallery.presenter;


import projects.com.codenicely.pehlakadam.gallery.OnGalleryApiResponse;
import projects.com.codenicely.pehlakadam.gallery.model.GalleryData;
import projects.com.codenicely.pehlakadam.gallery.provider.GalleryProvider;
import projects.com.codenicely.pehlakadam.gallery.view.GalleryView;
import projects.com.codenicely.pehlakadam.helper.Keys;

/**
 * Created by meghalagrawal on 03/06/17.
 */

public class GalleryPresenterImpl implements GalleryPresenter {

    private GalleryView galleryView;
    private GalleryProvider galleryProvider;

    public GalleryPresenterImpl(GalleryView galleryView, GalleryProvider galleryProvider) {
        this.galleryView = galleryView;
        this.galleryProvider = galleryProvider;
    }

    @Override
    public void getImages(final int language_type) {

        galleryView.showLoader(true);

        galleryProvider.getImages(language_type, new OnGalleryApiResponse() {
            @Override
            public void onSuccess(GalleryData galleryData) {
                if(galleryData.isSuccess()){

                    galleryView.showLoader(false);
                    galleryView.onGalleryData(galleryData.getImage_list());

                }else{

                    galleryView.showLoader(false);

                }
            }
            @Override
            public void onFailed(String message)
            {

                galleryView.showLoader(false);
                galleryView.showMessage(message);


            }
        });


    }

    @Override
    public void onDestroy() {
        if(galleryProvider!=null){
            galleryProvider.onDestroy();
        }
    }


}
