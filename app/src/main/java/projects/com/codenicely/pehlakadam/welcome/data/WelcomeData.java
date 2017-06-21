package projects.com.codenicely.pehlakadam.welcome.data;

import java.util.List;

import projects.com.codenicely.pehlakadam.login.data.WardDetails;

public class WelcomeData {

    private boolean success;
    private String message;
    List<WelcomePageDetails> welcome_page;
    List<WardDetails> ward_list;

    public WelcomeData(boolean success, String message, List<WelcomePageDetails> welcome_page,
                       List<WardDetails> ward_list) {
        this.success = success;
        this.message = message;
        this.welcome_page = welcome_page;
        this.ward_list = ward_list;
    }

    public List<WardDetails> getWard_list() {
        return ward_list;
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
