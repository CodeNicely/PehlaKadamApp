package projects.com.codenicely.pehlakadam.contact_us.model;


import projects.com.codenicely.pehlakadam.contact_us.ContactUsCallback;

/**
 * Created by meghal on 15/10/16.
 */

public interface ContactUsProvider {


    void requestContactUs(int lang_type,ContactUsCallback contactUsCallback);

    void onDestroy();

}
