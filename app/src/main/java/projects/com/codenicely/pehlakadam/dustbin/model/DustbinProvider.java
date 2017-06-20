package projects.com.codenicely.pehlakadam.dustbin.model;

import projects.com.codenicely.pehlakadam.dustbin.DustbinCallback;

/**
 * Created by ujjwal on 20/6/17.
 */
public interface DustbinProvider {
	void getDustbinData(Double latitude,Double longitude,DustbinCallback dustbinCallback);
}
