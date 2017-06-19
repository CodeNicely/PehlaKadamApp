package projects.com.codenicely.pehlakadam.welcome.data;

import java.util.List;

public class WelcomeData {

    private boolean success;
    private String message;
    List<WelcomePageDetails> welcome_page;
    List<WardDetails> wardDetailsList;

    public WelcomeData(boolean success, String message, List<WelcomePageDetails> welcome_page,
                       List<WardDetails> wardDetailsList) {
        this.success = success;
        this.message = message;
        this.welcome_page = welcome_page;
        this.wardDetailsList = wardDetailsList;
    }

    public List<WardDetails> getWardDetailsList() {
        return wardDetailsList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<WelcomePageDetails> getWelcome_page() {
        return welcome_page;
    }
}
