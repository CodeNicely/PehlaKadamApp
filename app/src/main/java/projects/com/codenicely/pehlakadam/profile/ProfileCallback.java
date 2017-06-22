package projects.com.codenicely.pehlakadam.profile;

import projects.com.codenicely.pehlakadam.profile.data.ProfileData;

/**
 * Created by ujjwal on 22/6/17.
 */
public interface ProfileCallback {
	void onSuccess(ProfileData profileData);
	void onFailure();
}
