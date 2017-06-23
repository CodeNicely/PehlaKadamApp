package projects.com.codenicely.pehlakadam.profile.presenter;

import android.net.Uri;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface ProfilePresenter {
	void requestProfile(String access_token ,int lang_type);

	void openCamera();

	void openGallery();

	void editProfile(String name, String mobile, String email,String ward,Uri imageUri);
}
