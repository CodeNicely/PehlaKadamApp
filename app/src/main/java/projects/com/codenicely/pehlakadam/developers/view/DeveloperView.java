package projects.com.codenicely.pehlakadam.developers.view;


import projects.com.codenicely.pehlakadam.developers.model.data.CompanyData;

/**
 * Created by meghal on 17/10/16.
 */

public interface DeveloperView {

    void showLoading(boolean show);

    void showMessage(String message);

    void setData(CompanyData companyData);


}
