package isshukan.com.bukatender.screen.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.BaseScreen;
import isshukan.com.bukatender.screen.activity.controller.LoginController;

public class LoginActivity extends AppCompatActivity implements BaseScreen, View.OnClickListener {

    private EditText usernameET;
    private EditText passwordET;
    private Button loginBtn;

    private LoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadViews();
        setDefaultSetting();
    }

    @Override
    public void loadViews() {
        usernameET = (EditText) findViewById(R.id.usernameET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        loginBtn = (Button) findViewById(R.id.loginBtn);
    }

    @Override
    public void setDefaultSetting() {
        controller = new LoginController(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

    @Override
    public void onClick(View v) {
        if(isControllerNotNull()){
            controller.login();
        }
    }

    public EditText getUsernameET() {
        return usernameET;
    }

    public EditText getPasswordET() {
        return passwordET;
    }

}
