package isshukan.com.bukatender.screen.activity.controller;

import android.app.Activity;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.dataaccess.api.APIHelper;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.screen.activity.MylapakActivity;
import isshukan.com.bukatender.support.utils.Authentication;
import isshukan.com.bukatender.support.utils.Formatter;

/**
 * Created by - on 26/05/2017.
 */

public class MylapakController {

    private MylapakActivity activity;
    private Mylapak mylapak;

    private boolean isPurposeForAddBid;

    public MylapakController(MylapakActivity activity) {
        this.activity = activity;
        handleIntent();
        if(isPurposeForAddBid){
            activity.getActionButton().setText("ADD TO BID");
        }else{
            activity.getActionButton().setText("BUY PRODUCT");
        }
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
        activity.getMylapakSellerTV().setText("Seller: " + mylapak.getSeller());
        activity.getMylapakFeedbackTV().setText("Feedback: " + String.valueOf(mylapak.getSellerPositiveFeedback())
                + " pros / " + String.valueOf(mylapak.getSellerNegativeFeedback()) + " cons");
    }

    public void handleIntent() {
        Intent intent = activity.getIntent();
        isPurposeForAddBid = intent.getStringExtra(Constant.PURPOSE).equals(Constant.PURPOSE_ADD_BID);
        if(isPurposeForAddBid) {
            this.mylapak = (Mylapak) intent.getSerializableExtra(Constant.PRODUCT);
            fetchData();
        } else {
            String productID = intent.getStringExtra(Constant.PRODUCT_ID);
            String userID = Authentication.getUserId();
            String token = Authentication.getUserToken();
            String auth = "Basic " + Base64.encodeToString((userID + ":" + token).getBytes(), Base64.NO_WRAP);
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", auth);
            header.put("Content-Type", "application/json");
            APIHelper.get(ConstantAPI.BUKALAPAK_PRODUCT_ENDPOINT + productID + ".json", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        MylapakController.this.mylapak = new Mylapak(jsonObject.getJSONObject("product"));
                        activity.configureViews(mylapak);
                    } catch (Exception e) {
                        Toast.makeText(activity, "Cannot fetch data\nPlease check your network connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(activity, "Cannot fetch data\nPlease check your network connection", Toast.LENGTH_SHORT).show();
                }
            }, header);
        }
    }

    public void onClick(int id) {
        if(id == R.id.actionButton){
            if(isPurposeForAddBid){
                Intent data = new Intent();
                data.putExtra(Constant.PRODUCT, mylapak);
                activity.setResult(Activity.RESULT_OK,data);
                activity.finish();
            }else{
                //TODO
            }
        }
    }
}
