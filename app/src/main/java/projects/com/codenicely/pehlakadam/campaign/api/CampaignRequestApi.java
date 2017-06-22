package projects.com.codenicely.pehlakadam.campaign.api;

import com.google.android.exoplayer.C;

import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignData;
import projects.com.codenicely.pehlakadam.helper.Urls;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by aman on 20/6/17.
 */

public interface CampaignRequestApi {

    @FormUrlEncoded
    @POST(Urls.REQUEST_CAMPAIGN)
    Call<CampaignData> requestCampaign(@Field("lang_type") int lang_type,
                                       @Field("campaign_type") int campaign_type);
}
