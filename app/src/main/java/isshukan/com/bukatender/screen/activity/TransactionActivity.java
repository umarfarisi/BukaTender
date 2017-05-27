package isshukan.com.bukatender.screen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import isshukan.com.bukatender.R;

public class TransactionActivity extends BaseActivity {

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_transaction);
    }

    @Override
    public void setDefaultSetting() {

    }

    @Override
    public boolean isControllerNotNull() {
        return false;
    }
}
