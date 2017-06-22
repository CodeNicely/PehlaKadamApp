package projects.com.codenicely.pehlakadam.join_us.presenter;

/**
 * Created by aman on 20/6/17.
 */

public interface JoinUsPresenter {

    void requestJoinUs(String access_token,String mobile, String email,String description);
    void onDestroy();
}
