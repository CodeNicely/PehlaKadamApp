package projects.com.codenicely.pehlakadam.about_us.data;

import java.util.List;

/**
 * Created by meghal on 13/10/16.
 */

public class AboutUsData {


    private boolean success;
    private String message;
    private String image;
    private String introduction;
	private String vision;
	private String achievement;

	private List<AboutUsDetails> about_us_list;

	public AboutUsData(boolean success, String message, String image, String introduction,
					   String vision, String achievement, List<AboutUsDetails> about_us_list) {
		this.success = success;
		this.message = message;
		this.image = image;
		this.introduction = introduction;
		this.vision = vision;
		this.achievement = achievement;
		this.about_us_list = about_us_list;
	}

	public List<AboutUsDetails> getAbout_us_list() {
		return about_us_list;
	}

	public String getIntroduction() {
		return introduction;
	}

	public String getVision() {
		return vision;
	}

	public String getAchievement() {
		return achievement;
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
