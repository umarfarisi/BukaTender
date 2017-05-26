package isshukan.com.bukatender.screen.activity.controller;

import android.content.Intent;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.screen.activity.MylapakActivity;
import isshukan.com.bukatender.support.utils.Formatter;

/**
 * Created by - on 26/05/2017.
 */

public class MylapakController {
    private MylapakActivity activity;
    private Mylapak mylapak;

    public MylapakController(MylapakActivity activity) {
        this.activity = activity;
        handleIntent();
    }

    public void fetchData() {
        if(mylapak.getImageURL() != null && !mylapak.getImageURL().isEmpty()){
            Picasso.with(activity).load(mylapak.getImageURL()).fit().into(activity.getMylapakIV());
        } else {
            Toast.makeText(activity, "Could not load image", Toast.LENGTH_SHORT).show();
        }
        activity.getMylapakRatingTV().setText("Rating: " + mylapak.getAvg_rate());
        activity.getMylapakRB().setRating((float) mylapak.getAvg_rate());
        activity.getMylapakUserCountTV().setText(mylapak.getView_count() + " views");
        activity.getMylapakInterestCountTV().setText(mylapak.getInterest_count() + " interested");
        activity.getMylapakNameTV().setText(mylapak.getTitle());
        activity.getMylapakPriceTV().setText(Formatter.priceFormatter(mylapak.getPrice()));
        activity.getMylapakStockTV().setText(String.valueOf(mylapak.getStock()));
        activity.getMylapakConditionTV().setText("Condition: " + mylapak.getCondition());
        activity.getMylapakDescriptionTV().setText(mylapak.getDescription());
    }

    public void handleIntent() {
        Intent intent = activity.getIntent();
        this.mylapak = (Mylapak) intent.getSerializableExtra("mylapak-object");
    }
}
