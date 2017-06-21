package projects.com.codenicely.pehlakadam.join_us;

import projects.com.codenicely.pehlakadam.join_us.model.data.JoinUsData;

/**
 * Created by aman on 20/6/17.
 */

public interface JoinUsCallBack {
    void onSuccess(JoinUsData joinUsData);
    void onFailure();
}
