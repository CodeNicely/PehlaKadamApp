package projects.com.codenicely.pehlakadam.about_us.model;


import projects.com.codenicely.pehlakadam.about_us.AboutUsCallBack;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsProvider {


    void requestAboutUs(int lang_type,AboutUsCallBack aboutUsCallBack);

    void onDestroy();

}
