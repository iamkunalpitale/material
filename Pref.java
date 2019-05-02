package shrinkcom.user.kaptap.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class TAPPref {

    private static TAPPref _instance = null;
    private static SharedPreferences _sPrefs,_sPrefs2 = null;
    private static SharedPreferences.Editor _editor = null;

    private static final String OMAN_APP_PREFS = "TapBykunal";
    private static final String OMAN_APP_PREFS_2 = "TapByKunals";
    public static final String PREFS_DEVICE_W = "device_w";
    public static final String PREFS_DEVICE_H = "device_h";

    public static final String PREFS_GCM_REG_ID = "reg_id";
    public static final String PREFS_USER_ID = "user_id";
    public static final String PREFS_IS_LOGGEDIN = "is_logged_in";
    public static final String PREFS_IS_LOGGEDIN_VIA_FB = "is_logged_in_via_fb";

    public static final String PREFS_LOGING_VIA = "logged_in_via";
    public static final String PREFS_FULL_NAME = "full_name";
    public static final String PREFS_FIRST_NAME = "first_name";
    public static final String PREFS_LAST_NAME = "last_name";
    public static final String PREFS_USER_TYPE = "user_type";
    public static final String PREFS_USER_NAME = "user_name";
    public static final String PREFS_USER_EMAIL = "user_email";
    public static final String PREFS_USER_FIRST_NAME = "user_first_name";
    public static final String PREFS_USER_LAST_NAME = "user_last_name";
    public static final String PREFS_USER_MOBILE = "user_mobile";
    public static final String PREFS_USER_IMAGE = "user_image";
    public static final String PREFS_USER_THUMB_IMAGE = "user_thumb_image";
    public static final String PREFS_DEFAULT_LANGUAGE = "default_language";
    public static final String PREFS_CURRENT_LATITUDE = "current_lat";
    public static final String PREFS_CURRENT_LONGITUDE = "current_lng";

    public static final String PREF_TW_ACCESS_TOKEN = "TW_ACCESS_TOKEN";
    public static final String PREF_TW_ACCESS_TOKEN_SECRET = "TW_ACCESS_TOKEN_SECRET";
    public static final String PREF_TW_IS_LOGIN = "is_tw_login";

    public static final String PREF_VIEWROUTE_DAY = "PREF_VIEWROUTE_DAY";


    public static final String PREFS_GOOGLEIT_LAT = "gooitlat";
    public static final String PREFS_GOOGLEIT_LON = "googleitlon";
    public static final String PREF_GOOGLEIT_PNAME = "googleitpname";

    public static final String PREFS_TRIP_ID = "tripid";
    public static final String PREFS_TRIP_NAME = "tripname";
    public static final String PREFS_TRIP_DATE = "tripdate";
    public static final String PREFS_TRIP_DAY = "tripday";
    public static final String PREFS_TRIP_SERVERDATE = "tripserverdate";
    public static final String PREFS_TRIP_IMAGESSS = "tripimageds";
    public static final String PREFS_POSITIONDAY = "positiondaysss";
    public static final String PREFS_SAVEDAYPOSITION = "savedayposition";



    public TAPPref() {
    }

    public TAPPref(Context context) {
        _sPrefs = context.getSharedPreferences(OMAN_APP_PREFS,
                Context.MODE_PRIVATE);

        _sPrefs2 = context.getSharedPreferences(OMAN_APP_PREFS_2,
                Context.MODE_PRIVATE);
    }

    public static TAPPref getInstance(Context context) {
        if (_instance == null) {
            _instance = new TAPPref(context);
        }
        return _instance;
    }

    public String readPrefs(String pref_name) {
        return _sPrefs.getString(pref_name, "");
    }

    public void writePrefs(String pref_name, String pref_val) {
        _editor = _sPrefs.edit();
        _editor.putString(pref_name, pref_val);
        _editor.commit();
    }

    public void clearPrefs() {
        _editor = _sPrefs.edit();
        _editor.clear();
        _editor.commit();
    }

    public boolean readBooleanPrefs(String pref_name) {
        return _sPrefs.getBoolean(pref_name, false);
    }

    public void writeBooleanPrefs(String pref_name, boolean pref_val) {
        _editor = _sPrefs.edit();
        _editor.putBoolean(pref_name, pref_val);
        _editor.commit();
    }

    public String readDefaultLangPrefs(String pref_name) {
        return _sPrefs.getString(pref_name, "");
    }

    public void writeDefaultLangPrefs(String pref_name) {
        _editor = _sPrefs.edit();
        _editor.putString(pref_name, pref_name);
        _editor.commit();
    }

    public String readLatLngPrefs(String pref_name) {
        return _sPrefs.getString(pref_name, "0.0");
    }

    public void writeLatLngPrefs(String pref_name, String pref_val) {
        _editor = _sPrefs.edit();
        _editor.putString(pref_name, pref_val);
        _editor.commit();
    }

    public String readBackupPrefs(String pref_name) {
        return _sPrefs2.getString(pref_name, "");
    }

    public void writeBackupPrefs(String pref_name, String pref_val) {
        SharedPreferences.Editor _editor = _sPrefs2.edit();
        _editor.putString(pref_name, pref_val);
        _editor.commit();
    }




}
