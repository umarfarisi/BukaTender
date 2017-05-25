package isshukan.com.bukatender.support.utils;

import android.content.Context;
import android.content.SharedPreferences;

import isshukan.com.bukatender.constant.Constant;

/**
 * @author Muhammad Umar Farisi
 * @created 25/05/2017
 */
public class Authentication {

    private static SharedPreferences preferences = GlobalVariable.APP_CONTEXT.getSharedPreferences(Constant.USER_ID_SHARED_PREFERENCES, Context.MODE_PRIVATE);

    public static void setUserId(String userId){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.USER_ID, userId);
        editor.apply();
    }

    public static void setUserToken(String userToken) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.USER_TOKEN, userToken);
        editor.apply();
    }

    public static void removeUserId(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(Constant.USER_ID);
        editor.apply();
    }

    public static void removeUserToken() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(Constant.USER_TOKEN);
        editor.apply();
    }

    public static String getUserId(){
        return preferences.getString(Constant.USER_ID, null);
    }

    public static String getUserToken() {
        return preferences.getString(Constant.USER_TOKEN, null);
    }
}
