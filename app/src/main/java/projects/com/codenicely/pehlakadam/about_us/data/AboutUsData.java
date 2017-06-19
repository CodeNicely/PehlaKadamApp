package projects.com.codenicely.pehlakadam.about_us.data;

import java.util.List;

/**
 * Created by meghal on 13/10/16.
 */

public class AboutUsData {


    private boolean success;
    private String message;
    private String image;
    private List<AboutUsDetails> aboutUsDetailsList;

	public AboutUsData(boolean success, String message, String image, List<AboutUsDetails> aboutUsDetailsList) {
		this.success = success;
		this.message = message;
		this.image = image;
		this.aboutUsDetailsList = aboutUsDetailsList;
	}

	public List<AboutUsDetails> getAboutUsDetailsList() {
		return aboutUsDetailsList;
	}

	public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getImage() {
        return image;
    }


}
