package projects.com.codenicely.pehlakadam.stories.model.data;

/**
 * Created by aman on 17/6/17.
 */

public class StoriesImageData {

    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public StoriesImageData(boolean success, String message) {

        this.success = success;
        this.message = message;
    }
}
