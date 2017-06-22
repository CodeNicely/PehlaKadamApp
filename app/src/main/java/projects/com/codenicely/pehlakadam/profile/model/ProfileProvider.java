package projects.com.codenicely.pehlakadam.profile.model;

import projects.com.codenicely.pehlakadam.profile.ProfileCallback;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface ProfileProvider {
	void requestProfile(String access_token , int lang_type, ProfileCallback profileCallback);
}
