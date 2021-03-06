package projects.com.codenicely.pehlakadam.helper.utils;

/**
 * Created by aman on 19/6/17.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Utils {


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
