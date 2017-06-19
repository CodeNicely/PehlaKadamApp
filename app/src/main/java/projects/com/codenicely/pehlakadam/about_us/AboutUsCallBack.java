package projects.com.codenicely.pehlakadam.about_us;


import projects.com.codenicely.pehlakadam.about_us.data.AboutUsData;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsCallBack {


    void onSuccess(AboutUsData aboutUsData);

    void onFailure();

}

