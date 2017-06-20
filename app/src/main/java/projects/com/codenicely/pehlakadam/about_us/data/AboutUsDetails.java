package projects.com.codenicely.pehlakadam.about_us.data;

/**
 * Created by ujjwal on 19/6/17.
 */
public class AboutUsDetails {
	private String title;
	private String description;
	private String image;

	public AboutUsDetails(String title, String description, String image) {
		this.title = title;
		this.description = description;
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
}
