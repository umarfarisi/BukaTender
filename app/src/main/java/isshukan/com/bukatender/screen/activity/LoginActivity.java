package isshukan.com.bukatender.screen.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.activity.controller.LoginController;
import isshukan.com.bukatender.support.utils.Authentication;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText usernameET;
    private EditText passwordET;
    private Button loginBtn;

    private LoginController controller;

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_login);
        usernameET = (EditText) findViewById(R.id.usernameET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        loginBtn = (Button) findViewById(R.id.loginBtn);
    }

    @Override
    public void setDefaultSetting() {
        controller = new LoginController(this);
        loginBtn.setOnClickListener(this);

        if(Authentication.getUserId() != null){
            startActivity(new Intent(this, MainActivity.class));
        }

    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

    @Override
    public void onClick(View v) {
        if(isControllerNotNull()){
            controller.login();
            Authentication.setUserId("123");
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public EditText getUsernameET() {
        return usernameET;
    }

    public EditText getPasswordET() {
        return passwordET;
    }

}
