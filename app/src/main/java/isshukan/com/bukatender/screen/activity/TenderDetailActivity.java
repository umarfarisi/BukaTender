package isshukan.com.bukatender.screen.activity;

import android.content.Intent;
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
    private Button listBidButton;

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
        listBidButton = (Button) findViewById(R.id.listBidButton);
    }

    @Override
    public void setDefaultSetting() {
        listBidButton.setOnClickListener(this);
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

    public FloatingActionButton getActionFloatingActionButton() {
        return actionFloatingActionButton;
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

    @Override
    public void onClick(View v) {
        if(isControllerNotNull()){
            controller.onClick(v.getId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(isControllerNotNull()){
            controller.onActivityResult(requestCode, resultCode, data);
        }
    }
}
