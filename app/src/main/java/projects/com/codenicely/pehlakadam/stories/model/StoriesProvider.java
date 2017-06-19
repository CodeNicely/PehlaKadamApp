package projects.com.codenicely.pehlakadam.stories.model;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.IOException;

import projects.com.codenicely.pehlakadam.stories.StoriesCallBack;
import projects.com.codenicely.pehlakadam.stories.StoriesLikeShareCallBack;
import projects.com.codenicely.pehlakadam.stories.model.data.StoriesLikeShareData;
import rx.Observable;

/**
 * Created by aman on 16/6/17.
 */

public interface StoriesProvider {

    void requestStories(String access_token, StoriesCallBack storiesCallBack);
    void requestLikeShare(String access_token, int story_id, int button_id,
                          StoriesLikeShareCallBack storiesLikeShareCallBack);
    Observable<StoriesLikeShareData> addStories(String access_token, String title,
                                                String description, Uri image)  throws IOException;
}
