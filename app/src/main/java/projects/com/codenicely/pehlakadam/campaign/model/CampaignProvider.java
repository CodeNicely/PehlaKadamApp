package projects.com.codenicely.pehlakadam.campaign.model;

import projects.com.codenicely.pehlakadam.campaign.CampaignCallBack;
import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignData;

/**
 * Created by aman on 20/6/17.
 */

public interface CampaignProvider {
    void requestCampaign(String lang_type, String campaign_type, CampaignCallBack campaignCallBack);
}
