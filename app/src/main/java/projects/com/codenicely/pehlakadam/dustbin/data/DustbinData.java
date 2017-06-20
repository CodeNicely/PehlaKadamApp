package projects.com.codenicely.pehlakadam.dustbin.data;

import java.util.List;

/**
 * Created by ujjwal on 20/6/17.
 */
public class DustbinData {
	private boolean success;
	private String message;
	List<DustbinDetails> dustbin_list;

	public DustbinData(boolean success, String message, List<DustbinDetails> dustbin_list) {
		this.success = success;
		this.message = message;
		this.dustbin_list = dustbin_list;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public List<DustbinDetails> getDustbin_list() {
		return dustbin_list;
	}

}
