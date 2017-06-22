package projects.com.codenicely.pehlakadam.developers;


import projects.com.codenicely.pehlakadam.developers.model.data.DeveloperData;

/**
 * Created by meghal on 17/10/16.
 */

public interface DevelopersCallback {

    void onSuccess(DeveloperData developerData);

    void onFailed();


}
