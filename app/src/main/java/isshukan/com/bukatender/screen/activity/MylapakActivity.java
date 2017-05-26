package isshukan.com.bukatender.screen.activity;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.activity.controller.MylapakController;

/**
 * Created by - on 26/05/2017.
 */

public class MylapakActivity extends BaseActivity {
    private TextView mylapakRatingTV;
    private TextView mylapakUserCountTV;
    private TextView mylapakInterestCountTV;
    private TextView mylapakNameTV;
    private TextView mylapakPriceTV;
    private TextView mylapakStockTV;
    private TextView mylapakConditionTV;
    private TextView mylapakDescriptionTV;
    private RatingBar mylapakRB;
    private ImageView mylapakIV;
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
        mylapakRB = (RatingBar) findViewById(R.id.mylapakRB);
        mylapakIV = (ImageView) findViewById(R.id.mylapakIV);
    }

    @Override
    public void setDefaultSetting() {
        controller = new MylapakController(this);
        controller.fetchData();
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
}
