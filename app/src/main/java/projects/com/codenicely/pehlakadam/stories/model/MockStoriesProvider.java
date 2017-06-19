package projects.com.codenicely.pehlakadam.stories.model;


import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.stories.StoriesCallBack;
import projects.com.codenicely.pehlakadam.stories.StoriesLikeShareCallBack;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesData;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesLikeShareData;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesListDetails;

/**
 * Created by aman on 17/6/17.
 */

public class MockStoriesProvider implements StoriesProvider {
    @Override
    public void requestStories(String access_token,final StoriesCallBack storiesCallBack) {
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                storiesCallBack.onSuccess(getMockStories());
             }
         },3000);
    }

    @Override
    public void requestLikeShare(String access_token, int story_id,
                                 int button_id, final StoriesLikeShareCallBack storiesLikeShareCallBack) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                storiesLikeShareCallBack.onSuccess(getMockStoriesLikeShare());
            }
        },3000);

    }

    @Override
    public void addStories(String access_token, String title, String description,
                           Bitmap image, final StoriesLikeShareCallBack storiesLikeShareCallBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                storiesLikeShareCallBack.onSuccess(sendMockStories());
            }


        },3000);
    }

    private StoriesLikeShareData sendMockStories() {
        return new StoriesLikeShareData(true,"Story Added");
    }


    public StoriesData getMockStories()
    {
        List<StoriesListDetails> storiesListDetailses=new ArrayList<>();
        for(int i=0;i<6;i++)
        {
            StoriesListDetails storiesListDetails= new StoriesListDetails(i,i+1,"UserName",
                    "https://drive.google.com/file/d/0BxXB_NzrYajbc0E2bkM4Y3VELUk/view?usp=sharing",
                    "Yesterday","8:00 PM", "https://drive.google.com/open?id=0BxXB_NzrYajbWVhkWnhuS3psYk0",
                    "Title","Posting on your business Page is a great way to let your customers and fans know what your business is doing. Here are some tips to help you get the most out of your updates.",
                    i,i,false,false);
            storiesListDetailses.add(storiesListDetails);

        }
        return new StoriesData(true,"Success",storiesListDetailses);
    }

    public StoriesLikeShareData getMockStoriesLikeShare() {


        return new StoriesLikeShareData(true,"Success");
    }
}
