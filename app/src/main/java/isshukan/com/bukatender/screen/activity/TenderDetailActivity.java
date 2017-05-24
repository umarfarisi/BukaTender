package isshukan.com.bukatender.screen.activity;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.activity.controller.TenderDetailController;

public class TenderDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView photoImageView;
    private TextView shortDescriptionTextView;
    private TextView validityPeriodTextView;
    private TextView startingPriceTextView;
    private TextView tagTextView;
    private FloatingActionButton actionFloatingActionButton;
    private Button listOfBidButton;

    private TenderDetailController controller;

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_tender_detail);
        photoImageView = (ImageView) findViewById(R.id.photoImageView);
        shortDescriptionTextView = (TextView) findViewById(R.id.shortDescriptionTextView);
        validityPeriodTextView = (TextView) findViewById(R.id.validityPeriodTextView);
        startingPriceTextView = (TextView) findViewById(R.id.startingPriceTextView);
        tagTextView = (TextView) findViewById(R.id.tagTextView);
        actionFloatingActionButton = (FloatingActionButton) findViewById(R.id.actionFloatingActionButton);
        listOfBidButton = (Button) findViewById(R.id.listOfBidButton);
    }

    @Override
    public void setDefaultSetting() {
        listOfBidButton.setOnClickListener(this);
        actionFloatingActionButton.setOnClickListener(this);
        controller = new TenderDetailController(this);
    }

    public ImageView getPhotoImageView() {
        return photoImageView;
    }

    public TextView getShortDescriptionTextView() {
        return shortDescriptionTextView;
    }

    public TextView getValidityPeriodTextView() {
        return validityPeriodTextView;
    }

    public TextView getStartingPriceTextView() {
        return startingPriceTextView;
    }

    public TextView getTagTextView() {
        return tagTextView;
    }

    @Override
    public boolean isControllerNotNull() {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
