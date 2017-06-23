package projects.com.codenicely.pehlakadam.profile.view;

import projects.com.codenicely.pehlakadam.profile.data.ProfileData;

/**
 * Created by ujjwal on 21/6/17.
 */
public interface ProfileView {
	void showProgressBar(boolean show);
	void showMessage(String message);
	void setData(ProfileData profileData);

	void showDialogLoader(boolean show);


	/**
	 * This method is for checking camera permission.
	 * Applicable only for devices with Api 23 or more.
	 *
	 * @return
	 */
	boolean checkPermissionForCamera();

	/**
	 * This method is for checking gallery permission.
	 * Applicable only for devices with api 23 or more.
	 *
	 * @return
	 */
	boolean checkPermissionForGallery();

	/**
	 * This function is for requesting camera permission if user does'nt have taken permission
	 * previously.
	 *
	 * @return
	 */
	boolean requestCameraPermission();

	/**
	 * This function is for requesting gallery permission if user does'nt have taken permission
	 * previously.
	 *
	 * @return
	 */

	boolean requestGalleryPermission();


	/**
	 * This method is called when user chooses to open camera.
	 */
	void showCamera();

	/**
	 * This method is called when user chooses to open gallery.
	 */

	void showGallery();

	void fileFromPath(String filePath);

}
