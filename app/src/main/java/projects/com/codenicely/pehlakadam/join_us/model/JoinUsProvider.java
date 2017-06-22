package projects.com.codenicely.pehlakadam.join_us.model;

import projects.com.codenicely.pehlakadam.join_us.JoinUsCallBack;

/**
 * Created by aman on 20/6/17.
 */

public interface JoinUsProvider {

    void requestJoinUs(String access_token ,String mobile, String email
                        ,String desc, JoinUsCallBack joinUsCallBack);

    void onDestroy();
}
