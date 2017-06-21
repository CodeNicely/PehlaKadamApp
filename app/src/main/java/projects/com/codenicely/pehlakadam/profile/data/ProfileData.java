package projects.com.codenicely.pehlakadam.profile.data;

import java.util.List;

import projects.com.codenicely.pehlakadam.login.data.WardDetails;

/**
 * Created by ujjwal on 21/6/17.
 */

public class ProfileData {
	private boolean success;
	private String message;
	private String name;
	private String mobile;
	private String ward;
	private String image;
	private List<WardDetails> wardDetailsList;

	public ProfileData(boolean success, String message, String name, String mobile, String ward,
					   String image, List<WardDetails> wardDetailsList) {
		this.success = success;
		this.message = message;
		this.name = name;
		this.mobile = mobile;
		this.ward = ward;
		this.image = image;
		this.wardDetailsList = wardDetailsList;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getWard() {
		return ward;
	}

	public String getImage() {
		return image;
	}

	public List<WardDetails> getWardDetailsList() {
		return wardDetailsList;
	}
}
