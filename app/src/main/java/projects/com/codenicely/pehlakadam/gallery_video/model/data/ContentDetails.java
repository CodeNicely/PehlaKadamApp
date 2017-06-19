package projects.com.codenicely.pehlakadam.gallery_video.model.data;

/**
 * Created by iket on 25/2/17.
 */

public class ContentDetails {

    private String image_url;
    private int type;
    private String video_url;

    public ContentDetails(String image_url, int type, String video_url) {
        this.image_url = image_url;
        this.type = type;
        this.video_url = video_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public int getType() {
        return type;
    }

    public String getVideo_url() {
        return video_url;
    }
}
