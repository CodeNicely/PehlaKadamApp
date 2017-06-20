package projects.com.codenicely.pehlakadam.stories.model.data;

/**
 * Created by aman on 16/6/17.
 */

public class StoriesListDetails {

    private int story_id;
    private int user_id;
    private String user_name;
    private String user_image;
    private String date;
    private String time;
    private String image;
    private String title;
    private String description;
    private int likes;
    private int shares;
    private boolean shared;
    private boolean liked;

    public StoriesListDetails(int story_id,int user_id, String user_name, String user_image, String date,
                              String time, String image, String title, String description,
                              int likes, int shares,boolean shared,boolean liked) {
        this.story_id=story_id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_image = user_image;
        this.date = date;
        this.time = time;
        this.image = image;
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.shares = shares;
        this.liked=liked;
        this.shared=shared;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getStory_id() {
        return story_id;
    }

    public boolean isShared() {
        return shared;
    }

    public boolean isLiked() {
        return liked;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_image() {
        return user_image;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getLikes() {
        return likes;
    }

    public int getShares() {
        return shares;
    }
}
