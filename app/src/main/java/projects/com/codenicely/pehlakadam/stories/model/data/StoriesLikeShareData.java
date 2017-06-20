package projects.com.codenicely.pehlakadam.stories.model.data;

/**
 * Created by aman on 20/6/17.
 */

public class StoriesLikeShareData {
    private boolean success;
    private String message;
    private int story_id;
    private int likes;
    private int shares;
    private boolean shared;
    private boolean liked;

    public StoriesLikeShareData(boolean success, String message, int story_id, int likes,
                                int shares, boolean shared, boolean liked) {
        this.success = success;
        this.message = message;
        this.story_id = story_id;
        this.likes = likes;
        this.shares = shares;
        this.shared = shared;
        this.liked = liked;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getStory_id() {
        return story_id;
    }

    public int getLikes() {
        return likes;
    }

    public int getShares() {
        return shares;
    }

    public boolean isShared() {
        return shared;
    }

    public boolean isLiked() {
        return liked;
    }
}
