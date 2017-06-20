package projects.com.codenicely.pehlakadam.campaign.model.data;

/**
 * Created by aman on 20/6/17.
 */

public class ImageListDetails {
    private int id;
    private String url;

    public ImageListDetails(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
