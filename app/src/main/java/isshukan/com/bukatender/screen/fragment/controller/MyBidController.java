package isshukan.com.bukatender.screen.fragment.controller;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import isshukan.com.bukatender.dataaccess.api.BidDA;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.screen.fragment.MyBidFragment;
import isshukan.com.bukatender.support.utils.APIUtils;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class MyBidController implements Response.Listener<String>, Response.ErrorListener {

    private MyBidFragment fragment;

    private BidDA bidDA;

    private List<Bid> bids;

    public MyBidController(MyBidFragment fragment) {
        this.fragment = fragment;
        loadData();
    }

    private void loadData() {
        bidDA = new BidDA();
        bids = new ArrayList<>();
        bidDA.getUserBid("123", this, this);
    }

    @Override
    public void onResponse(String response) {
        Log.d("ErroRRRRR", "RESONPONSE: "+response);
        try {
            JSONObject responseJO = new JSONObject(response);
            String status =responseJO.getString(APIUtils.STATUS);
            if(status.equals(APIUtils.STATUS_SUCCESS)){
                parserData(responseJO.getJSONArray(APIUtils.DATA));
            }
        } catch (JSONException e) {
            Toast.makeText(fragment.getContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            fragment.configureRecyclerView(bids);
        }
    }

    private void parserData(JSONArray dataJA) throws JSONException {
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

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(fragment.getContext(), "Error: "+error.getMessage(), Toast.LENGTH_LONG).show();
    }

}
