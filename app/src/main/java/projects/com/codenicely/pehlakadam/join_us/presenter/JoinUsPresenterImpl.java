package projects.com.codenicely.pehlakadam.join_us.presenter;

import android.content.Context;
import android.util.Log;

import projects.com.codenicely.pehlakadam.helper.Toaster;
import projects.com.codenicely.pehlakadam.join_us.JoinUsCallBack;
import projects.com.codenicely.pehlakadam.join_us.model.JoinUsProvider;
import projects.com.codenicely.pehlakadam.join_us.model.data.JoinUsData;
import projects.com.codenicely.pehlakadam.join_us.view.JoinUsView;

/**
 * Created by aman on 20/6/17.
 */

public class JoinUsPresenterImpl implements JoinUsPresenter {
    private JoinUsView joinUsView;
    private JoinUsProvider joinUsProvider;
    private Context context;
    private Toaster toaster;

    public JoinUsPresenterImpl( Context context,JoinUsView joinUsView,JoinUsProvider joinUsProvider) {
        this.joinUsView = joinUsView;
        this.joinUsProvider = joinUsProvider;
        this.context=context;
        toaster=new Toaster(context);
    }

    @Override
    public void requestJoinUs(String access_token,String desc) {
        Log.d("JoinUsPresenter","1");
        joinUsView.showProgressBar(true);
        joinUsProvider.requestJoinUs(access_token, desc, new JoinUsCallBack() {
            @Override
            public void onSuccess(JoinUsData joinUsData) {
                Log.d("JoinUsPresenter","success");
                try
                {
                    if (joinUsData.isSuccess()) {
                        joinUsView.showProgressBar(false);
                        joinUsView.showDialog(joinUsData.getMessage());

                    } else {
                        joinUsView.showProgressBar(false);
                        toaster.showMessage(joinUsData.getMessage());
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                    toaster.showMessage("Server Not Responding");
                }
            }

            @Override
            public void onFailure() {
                joinUsView.showProgressBar(false);
//                toaster.showDialog("No Internet Connection");
                joinUsView.showDialog("Something Went Wrong");
                Log.d("JoinUsPresenter","failure");
            }
        });
    }

    @Override
    public void onDestroy() {
        joinUsProvider.onDestroy();
    }
}
