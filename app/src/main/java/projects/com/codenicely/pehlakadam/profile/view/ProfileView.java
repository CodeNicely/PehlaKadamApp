package projects.com.codenicely.pehlakadam.profile.view;

import projects.com.codenicely.pehlakadam.profile.data.ProfileData;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface ProfileView {
	void showProgressBar(boolean show);
	void showMessage(String message);
	void setData(ProfileData profileData);

}
