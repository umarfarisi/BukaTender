package isshukan.com.bukatender.dataaccess.api;

import android.widget.Toast;

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
import isshukan.com.bukatender.support.utils.APIUtils;

/**
 * @author Muhammad Umar Farisi
 * @created 24/05/2017
 */
public class BidDA {

    private String url;

    public BidDA(){
        url = APIUtils.BASE_URL + APIUtils.BID_END_POINT;
    }

    public void getTenderBid(int tenderId, final DACallback<List<Bid>> callback){
        Map<String, String> parms = new HashMap<>();
        parms.put(APIUtils.METHOD, APIUtils.METHOD_READ);
        parms.put(APIUtils.TENDER_ID, String.valueOf(tenderId) );
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Bid> bids = new ArrayList<>();
                try {
                    JSONObject responseJO = new JSONObject(response);
                    String status =responseJO.getString(APIUtils.STATUS);
                    if(status.equals(APIUtils.STATUS_SUCCESS)){
                        parserJSONToListOfBid(responseJO.getJSONArray(APIUtils.DATA), bids);
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
        parms.put(APIUtils.METHOD, APIUtils.METHOD_READ);
        parms.put(APIUtils.USER_BID_ID, String.valueOf(userBidId) );
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Bid> bids = new ArrayList<>();
                try {
                    JSONObject responseJO = new JSONObject(response);
                    String status =responseJO.getString(APIUtils.STATUS);
                    if(status.equals(APIUtils.STATUS_SUCCESS)){
                        parserJSONToListOfBid(responseJO.getJSONArray(APIUtils.DATA), bids);
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
            int tenderId = dataJO.getInt(APIUtils.TENDER_ID);
            String productId = dataJO.getString(APIUtils.PRODUCT_ID);
            String userTenderId = dataJO.getString(APIUtils.USER_TENDER_ID);
            String userBidId = dataJO.getString(APIUtils.USER_BID_ID);
            String imageResource = dataJO.getString(APIUtils.IMAGE_RESOURCE);
            String titleProduct = dataJO.getString(APIUtils.TITLE_PRODUCT);
            double bidPrice = dataJO.getDouble(APIUtils.BID_PRICE);
            String shortDescription = dataJO.getString(APIUtils.SHORT_DESCRIPTION);
            bids.add(new Bid(tenderId, productId, userTenderId, userBidId, imageResource, titleProduct, bidPrice, shortDescription));
        }
    }


}
