package projects.com.codenicely.pehlakadam.about_us.data;

/**
 * Created by ujjwal on 19/6/17.
 */
public class AboutUsDetails {
	private String title;
	private String description;

	public AboutUsDetails(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
}
