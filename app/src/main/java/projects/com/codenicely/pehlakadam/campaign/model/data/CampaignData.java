package projects.com.codenicely.pehlakadam.campaign.model.data;

import java.util.List;

/**
 * Created by aman on 20/6/17.
 */

public class CampaignData {
    private boolean success;
    private String message;
    private List<CampaignListDetails> campaign_list;

    public CampaignData(boolean success, String message, List<CampaignListDetails> campaign_list) {
        this.success = success;
        this.message = message;
        this.campaign_list = campaign_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<CampaignListDetails> getPast() {
        return campaign_list;
    }
}
