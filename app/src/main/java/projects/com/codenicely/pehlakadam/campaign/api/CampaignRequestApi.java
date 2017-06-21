package projects.com.codenicely.pehlakadam.campaign.api;

import com.google.android.exoplayer.C;

import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignData;
import projects.com.codenicely.pehlakadam.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aman on 20/6/17.
 */

public interface CampaignRequestApi {

    @GET(Urls.REQUEST_CAMPAIGN)
    Call<CampaignData> requestCampaign(@Query("lang_type") String lang_type,
                                       @Query("campaign_type") String campaign_type);
}
