package projects.com.codenicely.pehlakadam.profile.model;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface ProfileProvider {
	void requestProfile(String access_token ,int lang_type );
}
