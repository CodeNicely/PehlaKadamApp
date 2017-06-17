package projects.com.codenicely.pehlakadam.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aman on 3/2/17.
 */
public class SharedPrefs {

    private static final String PREF_NAME = "welcome";
    private static final String PREF_NAME_LOGIN = "Login";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FCM = "fcm";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final int KEY_VERSION = 1;
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String PROFILE_IMAGE = "profile_image";

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

    public static String getProfileImage() {
        return PROFILE_IMAGE;
    }

    public void setProfileImage(String profile_image) {

        editor.putString(PROFILE_IMAGE, profile_image);
        editor.commit();

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

    public void setEmailId(String emailId) {

        editor.putString(KEY_EMAIL, emailId);
        editor.commit();

    }


    public String getAccessToken() {

        return pref.getString(KEY_ACCESS_TOKEN, null);
    }

    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getEmail() {

        return pref.getString(KEY_EMAIL, "Not Available");
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
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }




}
