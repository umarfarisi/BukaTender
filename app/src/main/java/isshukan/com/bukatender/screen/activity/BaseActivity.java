package isshukan.com.bukatender.screen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import isshukan.com.bukatender.R;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract void loadViews();
    protected abstract void setDefaultSetting();
    protected abstract boolean isControllerNotNull();
}
