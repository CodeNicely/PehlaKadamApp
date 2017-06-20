package projects.com.codenicely.pehlakadam.join_us.model.data;

/**
 * Created by aman on 20/6/17.
 */

public class JoinUsData {
    private String message;
    private boolean success;

    public JoinUsData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
