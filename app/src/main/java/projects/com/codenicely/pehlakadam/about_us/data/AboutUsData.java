package projects.com.codenicely.pehlakadam.about_us.data;

import java.util.List;

/**
 * Created by meghal on 13/10/16.
 */

public class AboutUsData {


    private boolean success;
    private String message;
    private String image;
    private List<AboutUsDetails> about_us_list;

	public AboutUsData(boolean success, String message, String image, List<AboutUsDetails> about_us_list) {
		this.success = success;
		this.message = message;
		this.image = image;
		this.about_us_list = about_us_list;
	}

	public List<AboutUsDetails> getAbout_us_list() {
		return about_us_list;
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
