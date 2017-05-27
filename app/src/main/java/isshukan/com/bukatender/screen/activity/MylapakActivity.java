package isshukan.com.bukatender.screen.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.screen.activity.controller.MylapakController;
import isshukan.com.bukatender.support.utils.Formatter;

/**
 * Created by - on 26/05/2017.
 */

public class MylapakActivity extends BaseActivity implements View.OnClickListener {
    private TextView mylapakRatingTV;
    private TextView mylapakUserCountTV;
    private TextView mylapakInterestCountTV;
    private TextView mylapakNameTV;
    private TextView mylapakPriceTV;
    private TextView mylapakStockTV;
    private TextView mylapakConditionTV;
    private TextView mylapakDescriptionTV;
    private TextView mylapakSellerTV;
    private TextView mylapakFeedbackTV;
    private RatingBar mylapakRB;
    private ImageView mylapakIV;
    private Button actionButton;

    private MylapakController controller;

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_mylapak);
        mylapakRatingTV = (TextView) findViewById(R.id.mylapakRatingTV);
        mylapakUserCountTV = (TextView) findViewById(R.id.mylapakUserCountTV);
        mylapakInterestCountTV = (TextView) findViewById(R.id.mylapakInterestCountTV);
        mylapakNameTV = (TextView) findViewById(R.id.mylapakNameTV);
        mylapakPriceTV = (TextView) findViewById(R.id.mylapakPriceTV);
        mylapakStockTV = (TextView) findViewById(R.id.mylapakStockTV);
        mylapakConditionTV = (TextView) findViewById(R.id.mylapakConditionTV);
        mylapakDescriptionTV = (TextView) findViewById(R.id.mylapakDescriptionTV);
        mylapakSellerTV = (TextView) findViewById(R.id.mylapakSellerTV);
        mylapakFeedbackTV = (TextView) findViewById(R.id.mylapakFeedbackTV);
        mylapakRB = (RatingBar) findViewById(R.id.mylapakRB);
        mylapakIV = (ImageView) findViewById(R.id.mylapakIV);
        actionButton = (Button) findViewById(R.id.actionButton);
    }

    @Override
    public void setDefaultSetting() {
        controller = new MylapakController(this);
        actionButton.setOnClickListener(this);
    }

    public void configureViews(Mylapak mylapak) {
        if(mylapak.getImageURL() != null && !mylapak.getImageURL().isEmpty()){
            Picasso.with(this).load(mylapak.getImageURL()).fit().into(this.getMylapakIV());
        } else {
            Toast.makeText(this, "Could not load image", Toast.LENGTH_SHORT).show();
        }
        this.getMylapakRatingTV().setText("Rating: " + mylapak.getAvg_rate());
        this.getMylapakRB().setRating((float) mylapak.getAvg_rate());
        this.getMylapakUserCountTV().setText(mylapak.getView_count() + " views");
        this.getMylapakInterestCountTV().setText(mylapak.getInterest_count() + " interested");
        this.getMylapakNameTV().setText(mylapak.getTitle());
        this.getMylapakPriceTV().setText(Formatter.priceFormatter(mylapak.getPrice()));
        this.getMylapakStockTV().setText(String.valueOf(mylapak.getStock()));
        this.getMylapakConditionTV().setText("Condition: " + mylapak.getCondition());
        this.getMylapakDescriptionTV().setText(mylapak.getDescription());
        this.getMylapakSellerTV().setText("Seller: " + mylapak.getSeller());
        this.getMylapakFeedbackTV().setText("Feedback: " + String.valueOf(mylapak.getSellerPositiveFeedback())
                + " pros / " + String.valueOf(mylapak.getSellerNegativeFeedback()) + " cons");
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

    public TextView getMylapakRatingTV() {
        return mylapakRatingTV;
    }

    public TextView getMylapakUserCountTV() {
        return mylapakUserCountTV;
    }

    public TextView getMylapakInterestCountTV() {
        return mylapakInterestCountTV;
    }

    public TextView getMylapakNameTV() {
        return mylapakNameTV;
    }

    public TextView getMylapakPriceTV() {
        return mylapakPriceTV;
    }

    public TextView getMylapakStockTV() {
        return mylapakStockTV;
    }

    public TextView getMylapakConditionTV() {
        return mylapakConditionTV;
    }

    public TextView getMylapakDescriptionTV() {
        return mylapakDescriptionTV;
    }

    public RatingBar getMylapakRB() {
        return mylapakRB;
    }

    public ImageView getMylapakIV() {
        return mylapakIV;
    }

    public Button getActionButton() {
        return actionButton;
    }

    public TextView getMylapakSellerTV() {
        return mylapakSellerTV;
    }

    public TextView getMylapakFeedbackTV() {
        return mylapakFeedbackTV;
    }

    @Override
    public void onClick(View v) {
        if(isControllerNotNull()){
            controller.onClick(v.getId());
        }
    }
}
