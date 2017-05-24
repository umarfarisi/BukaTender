package isshukan.com.bukatender.screen.activity.controller;

import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.dataaccess.BaseDA;
import isshukan.com.bukatender.dataaccess.UserDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.User;
import isshukan.com.bukatender.screen.activity.LoginActivity;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class LoginController {

    private LoginActivity activity;
    private BaseDA<User> userDA;

    public LoginController(LoginActivity activity) {
        this.activity = activity;
        this.userDA = new UserDA();
    }

    public void login() {

        String username = activity.getUsernameET().getText().toString();
        String password = activity.getPasswordET().getText().toString();

        Map<String, String> whereClause = new HashMap<>();

        userDA.get(whereClause, new DACallback<User>() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(activity,"Login success! "+user.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(activity,"Login failed!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
