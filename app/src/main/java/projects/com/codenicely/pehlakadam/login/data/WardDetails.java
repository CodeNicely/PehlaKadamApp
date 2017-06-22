package projects.com.codenicely.pehlakadam.login.data;

/**
 * Created by ujjwal on 17/6/17.
 */
public class WardDetails {
	private int id;
	private String name;

	public WardDetails(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
