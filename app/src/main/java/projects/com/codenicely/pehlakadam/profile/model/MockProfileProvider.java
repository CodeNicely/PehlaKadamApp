package projects.com.codenicely.pehlakadam.profile.model;

import java.util.ArrayList;
import java.util.List;

import projects.com.codenicely.pehlakadam.login.data.WardDetails;
import projects.com.codenicely.pehlakadam.profile.ProfileCallback;
import projects.com.codenicely.pehlakadam.profile.data.ProfileData;

/**
 * Created by ujjwal on 22/6/17.
 */
public class MockProfileProvider implements ProfileProvider {

	@Override
	public void requestProfile(String access_token, int lang_type, ProfileCallback profileCallback) {
		List<WardDetails> wardDetailsList = new ArrayList<>();
		profileCallback.onSuccess(new ProfileData(true,"Success","Ujjwal Agrawal","8770776846","ujjwal@gmail.com","Kota","",wardDetailsList));
	}
}
