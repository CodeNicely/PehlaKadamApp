package projects.com.codenicely.pehlakadam.contact_us;


import projects.com.codenicely.pehlakadam.contact_us.model.data.ContactUsData;

/**
 * Created by meghal on 15/10/16.
 */

public interface ContactUsCallback {

    void onSuccess(ContactUsData contactUsData);

    void onFailure();

}
