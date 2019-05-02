package shrinkcom.user.kaptap.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import shrinkcom.user.kaptap.Fragment.ProfileFragment;

/**
 * Created by dell on 12/27/2017.
 */

public class SessionManager {
    public static final String PREFERENCE_NAME = "userId";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }



    public int getCount() {
        int count = sharedpreferences.getInt("count", -1);
        return count;
    }

    public void setCount(int count) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("count", count);
        editor.commit();
    }

    public void clearCount() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove("count");
        editor.commit();
    }
    public void setUserid(String userid) {
        editor.putString("userId", userid);
        editor.commit();
    }

    public String getUserid() {
        String userid = sharedpreferences.getString("userId", "");
        return userid;

    }
}