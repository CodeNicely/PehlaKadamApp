package projects.com.codenicely.pehlakadam.stories.model.data;

import java.util.List;

/**
 * Created by aman on 16/6/17.
 */

public class StoriesData {
    private boolean success;
    private String message;
    private List<StoriesListDetails> stories_list;

    public StoriesData(boolean success, String message, List<StoriesListDetails> stories_list) {
        this.success = success;
        this.message = message;
        this.stories_list = stories_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<StoriesListDetails> getStories_list() {
        return stories_list;
    }
}
