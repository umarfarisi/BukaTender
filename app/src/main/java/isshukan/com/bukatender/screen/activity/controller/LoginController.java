package isshukan.com.bukatender.screen.activity.controller;

import android.util.Base64;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.dataaccess.api.APIHelper;
import android.content.Intent;

import isshukan.com.bukatender.screen.activity.ListMylapakActivity;
import isshukan.com.bukatender.screen.activity.LoginActivity;
import isshukan.com.bukatender.screen.activity.MainActivity;
import isshukan.com.bukatender.support.utils.Authentication;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class LoginController {

    private LoginActivity activity;

    public LoginController(LoginActivity activity) {
        this.activity = activity;
        if(Authentication.getUserId() != null && Authentication.getUserToken() != null){
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        }
    }

    public void login() {

        String username = activity.getUsernameET().getText().toString();
        String password = activity.getPasswordET().getText().toString();

        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(activity, "Username and Password Must be Entered", Toast.LENGTH_SHORT).show();
        }
        else {
            Map<String, String> header = new HashMap<>();
            String credentials = username + ":" + password;
            String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
            header.put("Authorization", auth);

            APIHelper.post(ConstantAPI.BUKALAPAK_USER_AUTH_ENDPOINT, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.getString("status").equals("OK")) {
                            Authentication.setUserId(jsonObject.getString("user_id"));
                            Authentication.setUserToken(jsonObject.getString("token"));
                            Toast.makeText(activity, "Login as " + jsonObject.getString("user_name"), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent();
                            //intent.setClass(activity, MainActivity.class);
                            intent.setClass(activity, ListMylapakActivity.class);
                            activity.startActivity(intent);
                            activity.finish();
                        } else {
                            Toast.makeText(activity, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(activity, "Cannot fetch data\nPlease check your network connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(activity, "Cannot fetch data\nPlease check your network connection", Toast.LENGTH_SHORT).show();
                }
            }, null, header);
        }

    }
}
