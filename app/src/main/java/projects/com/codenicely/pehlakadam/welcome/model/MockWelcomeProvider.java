package projects.com.codenicely.pehlakadam.welcome.model;

import android.os.Handler;


import java.util.ArrayList;
import java.util.List;

import projects.com.codenicely.pehlakadam.welcome.LoginCallback;
import projects.com.codenicely.pehlakadam.welcome.WelcomeCallBack;
import projects.com.codenicely.pehlakadam.login.data.WardDetails;
import projects.com.codenicely.pehlakadam.welcome.data.WelcomeData;
import projects.com.codenicely.pehlakadam.welcome.data.WelcomePageDetails;


public class MockWelcomeProvider implements WelcomeProvider {
    
    @Override
    public void requestWelcomeData(int lang_type,final WelcomeCallBack welcomeCallBack) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                welcomeCallBack.onSuccess(getMockPageDetails());
            }
        },500);

    }

	@Override
	public void requestLogin(String mobile, LoginCallback loginCallback) {

	}

	private WelcomeData getMockPageDetails(){
        List<WelcomePageDetails> welcomeDetailsList = new ArrayList<>();
        List<WardDetails> wardDetailsList = new ArrayList<>();
            WelcomePageDetails welcomeDetails = new WelcomePageDetails
                    (1,"Make your city Proud by keeping it Clean","1");

            welcomeDetailsList.add(welcomeDetails);
            WardDetails wardDetails = new WardDetails(1,"Kota ward");


            welcomeDetails = new WelcomePageDetails
                (2,"Find dustbins nearby you using our app","0");
            welcomeDetailsList.add(welcomeDetails);
//
//        welcomeDetails = new WelcomePageDetails
//                ("2","Have trouble finding dustbin?\n Request 1 near you","0");
//        welcomeDetailsList.add(welcomeDetails);
//
//        welcomeDetails = new WelcomePageDetails
//                ("2","Lets make our city beautiful \nTOGETHER","0");
//        welcomeDetailsList.add(welcomeDetails);




        WelcomeData welcomeData = new WelcomeData(true,"Success",welcomeDetailsList,wardDetailsList);

        return welcomeData;

    }

}
