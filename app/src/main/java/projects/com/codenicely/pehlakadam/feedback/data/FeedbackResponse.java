package projects.com.codenicely.pehlakadam.feedback.data;

/**
 * Created by ujjwal on 20/6/17.
 */
public class FeedbackResponse {
	private boolean success ;
	private String message;

	public FeedbackResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
