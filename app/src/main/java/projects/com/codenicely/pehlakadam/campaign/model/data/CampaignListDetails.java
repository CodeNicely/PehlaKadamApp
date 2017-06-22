package projects.com.codenicely.pehlakadam.campaign.model.data;

import java.util.List;

/**
 * Created by aman on 20/6/17.
 */

public class CampaignListDetails {
    private int id;
    private String image;
    private String name;
    private String date;
    private String description;
    private String venue;
    private List<ImageListDetails> image_list;

    public CampaignListDetails(int id,String image, String name, String date, String description,
                               String venue, List<ImageListDetails> image_list) {
        this.id = id;
        this.image=image;
        this.name = name;
        this.date = date;
        this.description = description;
        this.venue = venue;
        this.image_list = image_list;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public List<ImageListDetails> getImage_list() {
        return image_list;
    }
}
