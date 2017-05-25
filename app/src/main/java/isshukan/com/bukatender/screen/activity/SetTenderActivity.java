package isshukan.com.bukatender.screen.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.activity.controller.SetTenderController;

public class SetTenderActivity extends BaseActivity implements View.OnClickListener{

    private TextView titleTextView;
    private TextView startingPriceTextView;
    private TextView shortDescriptionTextView;
    private Button validityPeriodButton;
    private Button imageResourceButton;
    private Button actionButton;

    private SetTenderController controller;

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_set_tender);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        startingPriceTextView = (TextView) findViewById(R.id.startingPriceTextView);
        shortDescriptionTextView = (TextView) findViewById(R.id.shortDescriptionTextView);
        validityPeriodButton = (Button) findViewById(R.id.validityPeriodButton);
        imageResourceButton = (Button) findViewById(R.id.imageResourceButton);
        actionButton = (Button) findViewById(R.id.actionButton);
    }

    @Override
    public void setDefaultSetting() {
        validityPeriodButton.setOnClickListener(this);
        imageResourceButton.setOnClickListener(this);
        actionButton.setOnClickListener(this);
        controller = new SetTenderController(this);
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public TextView getStartingPriceTextView() {
        return startingPriceTextView;
    }

    public TextView getShortDescriptionTextView() {
        return shortDescriptionTextView;
    }

    public Button getActionButton() {
        return actionButton;
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

    @Override
    public void onClick(View v) {
        if(controller != null){
            controller.onClick(v.getId());
        }
    }
}
