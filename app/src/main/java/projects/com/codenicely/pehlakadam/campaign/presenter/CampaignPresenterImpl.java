package projects.com.codenicely.pehlakadam.campaign.presenter;

import android.content.Context;
import android.util.Log;

import projects.com.codenicely.pehlakadam.campaign.CampaignCallBack;
import projects.com.codenicely.pehlakadam.campaign.model.CampaignProvider;
import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignData;
import projects.com.codenicely.pehlakadam.campaign.view.CampaignView;
import projects.com.codenicely.pehlakadam.helper.Toaster;

/**
 * Created by aman on 20/6/17.
 */

public class CampaignPresenterImpl  implements  CampaignPresenter{

    private CampaignView campaignView;
    private CampaignProvider campaignProvider;
    private Context context;
    private Toaster toaster;

    public CampaignPresenterImpl(Context context,CampaignView campaignView, CampaignProvider campaignProvider) {
        this.campaignView = campaignView;
        this.campaignProvider = campaignProvider;
        this.context = context;
        toaster=new Toaster(context);
    }

    @Override
    public void requestCampaign(int lang_type, final int campaign_type) {
        campaignView.showProgressBar(true);
        campaignProvider.requestCampaign(lang_type, campaign_type, new CampaignCallBack() {
            @Override
            public void onSuccess(CampaignData campaignData) {
                try {
                    if(campaignData.isSuccess())
                    {
                        campaignView.showProgressBar(false);
                        campaignView.setData(campaignData,campaign_type);
                        toaster.showMessage(campaignData.getMessage());
                    }
                    else{
                        campaignView.showProgressBar(false);
                        toaster.showMessage(campaignData.getMessage());
                    }
                }catch (NullPointerException e) {
                    campaignView.showProgressBar(false);
                    toaster.showMessage("Success:NULL");
                }
            }

            @Override
            public void onFailure() {
                campaignView.showProgressBar(false);
                toaster.showMessage("No Internet Connection");
            }
        });

    }
}
