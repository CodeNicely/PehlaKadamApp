package projects.com.codenicely.pehlakadam.dustbin.presenter;

import projects.com.codenicely.pehlakadam.dustbin.model.DustbinProvider;
import projects.com.codenicely.pehlakadam.dustbin.view.DustbinView;

/**
 * Created by ujjwal on 20/6/17.
 */
public interface DustbinPresenter {
	void getDustbinData(Double latitude,Double longitude);
}
