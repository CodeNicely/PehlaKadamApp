package projects.com.codenicely.pehlakadam.stories.model.data;

/**
 * Created by aman on 17/6/17.
 */

public class StoriesLikeShareData {

    private boolean success;
    private String message;

    public StoriesLikeShareData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
