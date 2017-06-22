package projects.com.codenicely.pehlakadam.campaign.model;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import projects.com.codenicely.pehlakadam.campaign.CampaignCallBack;
import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignData;
import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignListDetails;
import projects.com.codenicely.pehlakadam.campaign.model.data.ImageListDetails;

/**
 * Created by aman on 21/6/17.
 */

public class MockCampaignProvider implements CampaignProvider {
    @Override
    public void requestCampaign(int lang_type, final int campaign_type, final CampaignCallBack campaignCallBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                campaignCallBack.onSuccess(getMockCampaignData(campaign_type));
            }
        },900);
    }
    public CampaignData getMockCampaignData(int c){
        CampaignData campaignData;
        List<CampaignListDetails> campaignListDetailses=new ArrayList<>();

        if (c==0)
        {
            List<ImageListDetails> imageListDetailses=new ArrayList<>();

            for (int i=0;i<5;i++)
            {
                ImageListDetails imageListDetails = new ImageListDetails(i,
                        "http://www.contactauthority.com/website-assests/images/campaign1.png");
                imageListDetailses.add(imageListDetails);
            }
            for (int i=0;i<5;i++)
            {
                CampaignListDetails campaignListDetails = new CampaignListDetails(i
                        ,"http://www.contactauthority.com/website-assests/images/campaign1.png",
                        "Swach Bharat","Tomorrow","Promote Cleanliness","Amapara",imageListDetailses);
                campaignListDetailses.add(campaignListDetails);
            }
            campaignData = new CampaignData(true,"Success",campaignListDetailses);

        }else{
            List<ImageListDetails> imageListDetailses=new ArrayList<>();
            for (int i=0;i<5;i++)
            {
                CampaignListDetails campaignListDetails = new CampaignListDetails(i
                        ,"http://www.contactauthority.com/website-assests/images/campaign1.png",
                        "Swach Bharat","Tomorrow","Promote Cleanliness","Amapara",imageListDetailses);
                campaignListDetailses.add(campaignListDetails);
            }
            campaignData = new CampaignData(true,"Success",campaignListDetailses);

        }
        return campaignData;
    }
}
