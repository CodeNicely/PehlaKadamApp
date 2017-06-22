package projects.com.codenicely.pehlakadam.stories.presenter;

import android.net.Uri;
import android.util.Log;


import java.io.File;
import java.io.IOException;
import java.util.List;

import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.helper.MyApplication;
import projects.com.codenicely.pehlakadam.stories.StoriesCallBack;
import projects.com.codenicely.pehlakadam.stories.StoriesLikeShareCallBack;
import projects.com.codenicely.pehlakadam.stories.model.StoriesProvider;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesImageData;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesLikeShareData;
import projects.com.codenicely.pehlakadam.stories.views.StoriesView;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static projects.com.codenicely.pehlakadam.helper.utils.FileProvider.requestNewFile;

/**
 * Created by aman on 16/6/17.
 */

public class StoriesPresenterImpl implements StoriesPresenter {

    private StoriesView storiesView;
    private StoriesProvider storiesProvider;
    private Observable<StoriesImageData> storiesLikeShareDataObservable;
    private Subscription subscription;

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
                    storiesView.setListData(storiesData);

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

    @Override
    public void requestLikeShare(String access_token, int story_id,int position,int button_id) {

        storiesProvider.requestLikeShare(access_token, story_id, button_id, new StoriesLikeShareCallBack() {
            @Override
            public void onSuccess(StoriesLikeShareData storiesLikeShareData) {
                try{
                    if(storiesLikeShareData.isSuccess())
                    {
                        storiesView.updateItemData(storiesLikeShareData);
                    }
                    else {

                    }
                }catch (NullPointerException e){
                    e.printStackTrace();

                }


            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @Override
    public void openCamera() {
        File image = requestNewFile();
        if (storiesView.checkPermissionForCamera()) {
            Log.d("StoriesPresenter","Camera if");
            storiesView.fileFromPath(image.getPath());
            storiesView.showCamera();
        } else {
            Log.d("StoriesPresenter","Camera Else");
            if (storiesView.requestCameraPermission()) {
                Log.d("StoriesPresenter","Camera Else if");
                storiesView.fileFromPath(image.getPath());
                storiesView.showCamera();
            }
        }

    }

    @Override
    public void openGallery() {
        if (storiesView.checkPermissionForGallery()) {
            Log.d("StoriesPresenter","Gallery if");
            storiesView.showGallery();
        } else {
            Log.d("StoriesPresenter","Gallery else");
            if (storiesView.requestGalleryPermission()) {
                Log.d("StoriesPresenter","Gallery else if");
                storiesView.showGallery();
            }else
            {

            }
        }

    }

    @Override
    public void addStories(final String access_token, String title, String description, Uri imageUri) {
        storiesView.showDialogLoader(true);
        storiesView.disableButton(true);
        try {
            storiesLikeShareDataObservable = storiesProvider.addStories(access_token,title,
                                                                        description,imageUri);
            subscription = storiesLikeShareDataObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<StoriesImageData>() {
                        @Override
                        public void onCompleted() {
                            storiesView.showProgressBar(false);
                            storiesView.disableButton(false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            storiesView.showProgressBar(false);
                            storiesView.disableButton(false);
                            storiesView.showMessage(MyApplication.getContext().getResources()
                                    .getString(R.string.failure_message));
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(StoriesImageData storiesImageData) {
                            if (storiesImageData.isSuccess()){
                                requestStories(access_token);
                            }else {
                            }
                            storiesView.showDialogLoader(false);
                            storiesView.showMessage(storiesImageData.getMessage());
                            storiesView.showProgressBar(false);
                            storiesView.disableButton(false);
                        }

                    });

        }catch (IOException e) {
            e.printStackTrace();
            storiesView.showDialogLoader(false);
            storiesView.showMessage("Try Again");
            storiesView.showProgressBar(false);
            storiesView.disableButton(false);
        }
    }
}
