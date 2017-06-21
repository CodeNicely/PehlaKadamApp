package projects.com.codenicely.pehlakadam.campaign.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.com.codenicely.pehlakadam.campaign.CampaignCallBack;
import projects.com.codenicely.pehlakadam.campaign.api.CampaignRequestApi;
import projects.com.codenicely.pehlakadam.campaign.model.data.CampaignData;
import projects.com.codenicely.pehlakadam.helper.Urls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aman on 20/6/17.
 */

public class RetrofitCampaignProvider implements CampaignProvider {
    private CampaignRequestApi campaignRequestApi;
    private Retrofit retrofit;
    Call<CampaignData> call;

    public RetrofitCampaignProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        campaignRequestApi = retrofit.create(CampaignRequestApi.class);
    }

    @Override
    public void requestCampaign(int lang_type, int campaign_type,
                                final CampaignCallBack campaignCallBack) {
        call= campaignRequestApi.requestCampaign(lang_type,campaign_type);
        call.enqueue(new Callback<CampaignData>() {
            @Override
            public void onResponse(Call<CampaignData> call, Response<CampaignData> response) {
                campaignCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CampaignData> call, Throwable t) {
                t.printStackTrace();
                campaignCallBack.onFailure();
            }
        });
    }
}
