package projects.com.codenicely.pehlakadam.dustbin.data;

/**
 * Created by ujjwal on 20/6/17.
 */
public class DustbinDetails {
	private Double latitude;
	private Double longitude;
	private String name;
	private float distance;

	public DustbinDetails(Double latitude, Double longitude, String name, float distance) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.distance = distance;
	}

	public float getDistance() {
		return distance;
	}

	public String getName() {
		return name;
	}


	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}
}
