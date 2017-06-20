package projects.com.codenicely.pehlakadam.feedback;

import projects.com.codenicely.pehlakadam.feedback.data.FeedbackResponse;

/**
 * Created by ujjwal on 20/6/17.
 */
public interface FeedbackCallback {
	void onSuccess(FeedbackResponse feedbackResponse);
	void onFailed();
}
