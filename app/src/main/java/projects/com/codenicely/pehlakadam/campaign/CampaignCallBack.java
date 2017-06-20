package projects.com.codenicely.pehlakadam.campaign;

import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignData;

/**
 * Created by aman on 20/6/17.
 */

public interface CampaignCallBack {
    void onSuccess(CampaignData campaignData);
    void onFailure();
}
