package projects.com.codenicely.pehlakadam.dustbin;

import projects.com.codenicely.pehlakadam.dustbin.data.DustbinData;

/**
 * Created by ujjwal on 20/6/17.
 */
public interface DustbinCallback {
	void onSuccess(DustbinData dustbinData);
	void onFailure();
}
