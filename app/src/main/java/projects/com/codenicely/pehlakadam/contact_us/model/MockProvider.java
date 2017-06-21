package projects.com.codenicely.pehlakadam.contact_us.model;

import projects.com.codenicely.pehlakadam.contact_us.ContactUsCallback;
import projects.com.codenicely.pehlakadam.contact_us.model.data.ContactUsData;

/**
 * Created by aman on 19/6/17.
 */

public class MockProvider implements  ContactUsProvider {
    @Override
    public void requestContactUs(int lang_type,ContactUsCallback contactUsCallback) {
        contactUsCallback.onSuccess(new ContactUsData(true,"Success","Codenicely@gmail.com","8109573930",
                "Raipur","","www.gmail.com"));

    }

    @Override
    public void onDestroy() {

    }
}
