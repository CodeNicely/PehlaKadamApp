package projects.com.codenicely.pehlakadam.gallery_video.model.data;

import java.util.List;

/**
 * Created by meghal on 13/10/16.
 */

public class GalleryData {


    private boolean success;
    private String message;
    private List<ContentDetails> content_details;

    public GalleryData(boolean success, String message, List<ContentDetails> content_details) {
        this.success = success;
        this.message = message;
        this.content_details = content_details;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }


    public List<ContentDetails> getContent_details() {
        return content_details;
    }
}
