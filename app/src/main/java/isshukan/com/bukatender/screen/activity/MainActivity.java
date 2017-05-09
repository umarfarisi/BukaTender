package isshukan.com.bukatender.screen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.BaseScreen;
import isshukan.com.bukatender.screen.activity.controller.MainController;

public class MainActivity extends AppCompatActivity implements BaseScreen{

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void loadViews() {

    }

    @Override
    public void setDefaultSetting() {
        controller = new MainController(this);
    }

    @Override
    public boolean isControllerNotNull() {
        return false;
    }
}
