package projects.com.codenicely.pehlakadam.feedback.model;

import projects.com.codenicely.pehlakadam.feedback.FeedbackCallback;

/**
 * Created by ujjwal on 20/6/17.
 */
public interface FeedbackHelper {
	void sendFeedback(String access_token, String feedback, FeedbackCallback feedbackCallback);
}
