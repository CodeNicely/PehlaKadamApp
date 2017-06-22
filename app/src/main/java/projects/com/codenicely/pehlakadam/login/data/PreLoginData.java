package projects.com.codenicely.pehlakadam.login.data;

import java.util.List;

/**
 * Created by ujjwal on 21/6/17.
 */
public class PreLoginData {
	private boolean success;
	private String message;
	List<WardDetails> ward_list;

	public PreLoginData(boolean success, String message, List<WardDetails> ward_list) {
		this.success = success;
		this.message = message;
		this.ward_list = ward_list;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public List<WardDetails> getWard_list() {
		return ward_list;
	}
}
