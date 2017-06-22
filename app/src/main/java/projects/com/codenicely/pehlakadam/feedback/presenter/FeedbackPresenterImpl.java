package projects.com.codenicely.pehlakadam.feedback.presenter;

import projects.com.codenicely.pehlakadam.feedback.FeedbackCallback;
import projects.com.codenicely.pehlakadam.feedback.data.FeedbackResponse;
import projects.com.codenicely.pehlakadam.feedback.model.FeedbackHelper;
import projects.com.codenicely.pehlakadam.feedback.view.FeedbackView;

/**
 * Created by ujjwal on 20/6/17.
 */
public class FeedbackPresenterImpl implements FeedbackPresenter{
	FeedbackView feedbackView;
	FeedbackHelper feedbackHelper;

	public FeedbackPresenterImpl(FeedbackView feedbackView, FeedbackHelper feedbackHelper) {
		this.feedbackView = feedbackView;
		this.feedbackHelper = feedbackHelper;
	}

	@Override
	public void sendFeedback(String access_token, String feedback) {
		feedbackView.showProgressBar(true);
		feedbackHelper.sendFeedback(access_token, feedback, new FeedbackCallback() {
			@Override
			public void onSuccess(FeedbackResponse feedbackResponse) {

				feedbackView.showProgressBar(false);
				feedbackView.showFeedbackDialog(feedbackResponse.getMessage());

			}

			@Override
			public void onFailed() {
				feedbackView.showProgressBar(false);
				feedbackView.showFeedbackDialog("Unable to connect to server");
			}
		});
	}
}
