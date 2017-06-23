package projects.com.codenicely.pehlakadam.about_us.data;

/**
 * Created by ujjwal on 19/6/17.
 */
public class AboutUsDetails {
	private String name;
	private String mobile;
	private String email;
	private String image;

	public AboutUsDetails(String name, String mobile, String email, String image) {
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.image = image;
	}


	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getImage() {
		return image;
	}
}
