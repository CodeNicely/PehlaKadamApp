package projects.com.codenicely.pehlakadam.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aman on 3/2/17.
 */
public class SharedPrefs {

    private static final String PREF_NAME = "welcome";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_FCM = "fcm";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_USER_LANGUAGE = "lang_type";
    private static final int KEY_VERSION = 1;
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    // LogCat tag
    private static String TAG = "Shared Preference";

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static int getKeyVersion() {
        return KEY_VERSION;
    }

    public String getUsername() {
        return pref.getString(KEY_USERNAME, "Not Available");
    }

    public void setUsername(String username) {

        editor.putString(KEY_USERNAME, username);
        editor.commit();


    }



    public String getAccessToken() {

        return pref.getString(KEY_ACCESS_TOKEN, "1");
    }

    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public int getUserLanguage() {
        return pref.getInt(KEY_USER_LANGUAGE, 0);
    }

    public void setUserLanguage(int userLanguage) {
        editor.putInt(KEY_USER_LANGUAGE, userLanguage);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    //Welcome_Screen
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public static String getKeyFcm() {
        return KEY_FCM;
    }
    public void setFcm(String fcm){editor.putString(KEY_FCM, fcm);
        editor.commit();

    }



}
