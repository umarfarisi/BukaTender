package isshukan.com.bukatender.dataaccess.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.constant.ConstantAPI;

/**
 * @author Muhammad Umar Farisi
 * @created 24/05/2017
 */
public class BidDA {

    private String url;

    public BidDA(){
        url = ConstantAPI.BASE_URL + ConstantAPI.BID_END_POINT;
    }

    public void getTenderBid(int tenderId, final DACallback<List<Bid>> callback){
        Map<String, String> parms = new HashMap<>();
        parms.put(ConstantAPI.METHOD, ConstantAPI.METHOD_READ);
        parms.put(ConstantAPI.TENDER_ID, String.valueOf(tenderId) );
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Bid> bids = new ArrayList<>();
                try {
                    JSONObject responseJO = new JSONObject(response);
                    String status =responseJO.getString(ConstantAPI.STATUS);
                    if(status.equals(ConstantAPI.STATUS_SUCCESS)){
                        parserJSONToListOfBid(responseJO.getJSONArray(ConstantAPI.DATA), bids);
                        callback.onSuccess(bids);
                    }else{
                        callback.onFailure("STATUS IS FAILED");
                    }
                } catch (JSONException e) {
                    callback.onFailure("ERROR: "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure("ERROR: "+error.getMessage());
            }
        }, parms);
    }

    public void getUserBid(String userBidId, final DACallback<List<Bid>> callback){
        Map<String, String> parms = new HashMap<>();
        parms.put(ConstantAPI.METHOD, ConstantAPI.METHOD_READ);
        parms.put(ConstantAPI.USER_BID_ID, String.valueOf(userBidId) );
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Bid> bids = new ArrayList<>();
                try {
                    JSONObject responseJO = new JSONObject(response);
                    String status =responseJO.getString(ConstantAPI.STATUS);
                    if(status.equals(ConstantAPI.STATUS_SUCCESS)){
                        parserJSONToListOfBid(responseJO.getJSONArray(ConstantAPI.DATA), bids);
                        callback.onSuccess(bids);
                    }else{
                        callback.onFailure("STATUS IS FAILED");
                    }
                } catch (JSONException e) {
                    callback.onFailure("ERROR: "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure("ERROR: "+error.getMessage());
            }
        }, parms);
    }

    private void parserJSONToListOfBid(JSONArray dataJA, List<Bid> bids) throws JSONException {
        for(int i = 0 ; i < dataJA.length() ; i++){
            JSONObject dataJO = dataJA.getJSONObject(i);
            int tenderId = dataJO.getInt(ConstantAPI.TENDER_ID);
            String productId = dataJO.getString(ConstantAPI.PRODUCT_ID);
            String userTenderId = dataJO.getString(ConstantAPI.USER_TENDER_ID);
            String userBidId = dataJO.getString(ConstantAPI.USER_BID_ID);
            String imageResource = dataJO.getString(ConstantAPI.IMAGE_RESOURCE);
            String titleProduct = dataJO.getString(ConstantAPI.TITLE_PRODUCT);
            double bidPrice = dataJO.getDouble(ConstantAPI.BID_PRICE);
            String shortDescription = dataJO.getString(ConstantAPI.SHORT_DESCRIPTION);
            bids.add(new Bid(tenderId, productId, userTenderId, userBidId, imageResource, titleProduct, bidPrice, shortDescription));
        }
    }


}
