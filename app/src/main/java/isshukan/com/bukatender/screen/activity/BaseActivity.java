package isshukan.com.bukatender.screen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import isshukan.com.bukatender.screen.BaseScreen;

/**
 * @author Muhammad Umar Farisi
 * @created 10/05/2017
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseScreen {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadViews();
        setDefaultSetting();
    }
}
