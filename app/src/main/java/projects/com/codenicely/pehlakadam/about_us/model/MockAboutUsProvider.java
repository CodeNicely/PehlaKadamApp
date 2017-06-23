package projects.com.codenicely.pehlakadam.about_us.model;

import java.util.ArrayList;
import java.util.List;

import projects.com.codenicely.pehlakadam.about_us.AboutUsCallBack;
import projects.com.codenicely.pehlakadam.about_us.data.AboutUsData;
import projects.com.codenicely.pehlakadam.about_us.data.AboutUsDetails;

/**
 * Created by ujjwal on 20/6/17.
 */
public class MockAboutUsProvider implements AboutUsProvider {
	@Override
	public void requestAboutUs(int lang_type, AboutUsCallBack aboutUsCallBack) {
		List<AboutUsDetails> aboutUsDetailsList = new ArrayList<>();

//		for (int i=0;i<5;i++){
//			AboutUsDetails aboutUsDetails = new AboutUsDetails("Title "+i,"Description "+i,"image");
//			aboutUsDetailsList.add(aboutUsDetails);
//		}

		aboutUsCallBack
				.onSuccess(new AboutUsData(true,"Success","","","","www.facebook.com",aboutUsDetailsList));
	}

	@Override
	public void onDestroy() {

	}
}
