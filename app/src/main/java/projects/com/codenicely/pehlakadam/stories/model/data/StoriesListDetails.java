package projects.com.codenicely.pehlakadam.stories.model.data;

/**
 * Created by aman on 16/6/17.
 */

public class StoriesListDetails {

    private int user_id;
    private String user_name;
    private String user_image;
    private String date;
    private String time;
    private String image;
    private String title;
    private String description;

    public StoriesListDetails(int user_id, String user_name,
                              String user_image, String date, String time,
                              String image, String title, String description) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_image = user_image;
        this.date = date;
        this.time = time;
        this.image = image;
        this.title = title;
        this.description = description;
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
}
