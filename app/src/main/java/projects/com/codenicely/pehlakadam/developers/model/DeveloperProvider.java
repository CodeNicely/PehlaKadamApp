package projects.com.codenicely.pehlakadam.developers.model;


import projects.com.codenicely.pehlakadam.developers.DevelopersCallback;

/**
 * Created by meghal on 17/10/16.
 */

public interface DeveloperProvider {

    void requestDevelopersData(DevelopersCallback developersCallback);

    void onDestroy();
}
