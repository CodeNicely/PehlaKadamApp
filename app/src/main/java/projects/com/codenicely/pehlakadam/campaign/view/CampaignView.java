package projects.com.codenicely.pehlakadam.campaign.view;

import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignData;

/**
 * Created by aman on 20/6/17.
 */

public interface CampaignView {
    void showProgressBar(boolean show);
    void setData(CampaignData campaignData,int c);

}
