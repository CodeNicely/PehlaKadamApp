package projects.com.codenicely.pehlakadam.developers.model.data;

/**
 * Created by meghal on 17/10/16.
 */

public class DeveloperData {

    private boolean success;
    private String message;
    private CompanyData developer_data;


    public DeveloperData(boolean success, String message, CompanyData developer_data) {
        this.success = success;
        this.message = message;
        this.developer_data = developer_data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public CompanyData getDeveloper_data() {
        return developer_data;
    }
}
