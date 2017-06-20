package projects.com.codenicely.pehlakadam.helper;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by aman on 20/6/17.
 */

public class Toaster {
    private  Context context;


    public Toaster(Context context) {
        this.context = context;
    }

    public void showMessage(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT ).show();
    }
}
