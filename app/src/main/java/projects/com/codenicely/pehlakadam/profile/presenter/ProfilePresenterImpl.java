package projects.com.codenicely.pehlakadam.profile.presenter;

import android.net.Uri;

import java.io.File;

import projects.com.codenicely.pehlakadam.profile.ProfileCallback;
import projects.com.codenicely.pehlakadam.profile.data.ProfileData;
import projects.com.codenicely.pehlakadam.profile.model.ProfileProvider;
import projects.com.codenicely.pehlakadam.profile.view.ProfileView;

import static projects.com.codenicely.pehlakadam.helper.utils.FileProvider.requestNewFile;

/**
 * Created by ujjwal on 22/6/17.
 */
public class ProfilePresenterImpl implements ProfilePresenter{

	ProfileView profileView;
	ProfileProvider profileProvider;

	public ProfilePresenterImpl(ProfileView profileView, ProfileProvider profileProvider) {
		this.profileView = profileView;
		this.profileProvider = profileProvider;
	}

	@Override
	public void requestProfile(String access_token, int lang_type) {
		profileView.showProgressBar(true);
		profileProvider.requestProfile(access_token,lang_type, new ProfileCallback() {
			@Override
			public void onSuccess(ProfileData profileData) {
				profileView.showProgressBar(false);
				if (profileData.isSuccess()){
					profileView.setData(profileData);
				}else {
					profileView.showMessage(profileData.getMessage());
				}
			}

			@Override
			public void onFailure() {
				profileView.showProgressBar(false);
				profileView.showMessage("Unable to connect to Server");
			}
		});
	}

	@Override
	public void openCamera() {
		File image = requestNewFile();
		if (profileView.checkPermissionForCamera()) {
			profileView.fileFromPath(image.getPath());
			profileView.showCamera();
		} else {
			if (profileView.requestCameraPermission()) {
				profileView.fileFromPath(image.getPath());
				profileView.showCamera();
			}
		}
	}

	@Override
	public void openGallery() {

	}

	@Override
	public void editProfile(String name, String mobile, String email, String ward, Uri imageUri) {

	}
}
