package isshukan.com.bukatender.dataaccess.api;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

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

    public void getTenderBid(int tenderId, Response.Listener<String> listener, Response.ErrorListener errorListener){
        Map<String, String> parms = new HashMap<>();
        parms.put(APIUtils.METHOD, APIUtils.METHOD_READ);
        parms.put(APIUtils.TENDER_ID, String.valueOf(tenderId) );
        APIHelper.post(url, listener, errorListener, parms);
    }

    public void getUserBid(String userBidId, Response.Listener<String> listener, Response.ErrorListener errorListener){
        Map<String, String> parms = new HashMap<>();
        parms.put(APIUtils.METHOD, APIUtils.METHOD_READ);
        parms.put(APIUtils.USER_BID_ID, String.valueOf(userBidId) );
        APIHelper.post(url, listener, errorListener, parms);
    }

}
