package projects.com.codenicely.pehlakadam.dustbin.view;

import projects.com.codenicely.pehlakadam.dustbin.data.DustbinData;

/**
 * Created by ujjwal on 20/6/17.
 */
public interface DustbinView {
	void showLoader(boolean show);
	void showMessage(String message);
	void setData(DustbinData dustbinData);
}
