package isshukan.com.bukatender.dataaccess.api;

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.support.utils.APIUtils;

/**
 * @author Muhammad Umar Farisi
 * @created 22/05/2017
 */
public class TenderDA {

    public void createTender(Tender newTender, Response.Listener<String> listener, Response.ErrorListener errorListener){
        Map<String,String> params = new HashMap<>();
        params.put(APIUtils.METHOD , APIUtils.METHOD_CREATE);
        params.put(APIUtils.TENDER_ID, newTender.getTenderId());
        params.put(APIUtils.USER_ID, newTender.getTenderId());
        params.put(APIUtils.TITLE, newTender.getTitle());
        params.put(APIUtils.VALIDITY_PERIOD, String.valueOf(newTender.getValidityPeriod()));
        params.put(APIUtils.STARTING_PRICE, String.valueOf(newTender.getStartingPrice()));
        params.put(APIUtils.IMAGE_RESOURCE, newTender.getImageResource());
        APIHelper.post(APIUtils.BASE_URL+APIUtils.TENDER_END_POINT, listener, errorListener, params);
    }

    public void getAllTender(Response.Listener<String> listener, Response.ErrorListener errorListener){
        Map<String,String> params = new HashMap<>();
        params.put(APIUtils.METHOD , APIUtils.METHOD_READ);
        APIHelper.post(APIUtils.BASE_URL+APIUtils.TENDER_END_POINT, listener, errorListener, params);
    }

    public void getUserTender(String userId ,Response.Listener<String> listener, Response.ErrorListener errorListener){
        Map<String,String> params = new HashMap<>();
        params.put(APIUtils.METHOD , APIUtils.METHOD_READ);
        params.put(APIUtils.USER_ID, userId);
        APIHelper.post(APIUtils.BASE_URL+APIUtils.TENDER_END_POINT, listener, errorListener, params);
    }

    public void updateTender(Tender oldTender, Tender newTender ,Response.Listener<String> listener, Response.ErrorListener errorListener){
        Map<String,String> params = new HashMap<>();
        params.put(APIUtils.METHOD , APIUtils.METHOD_UPDATE);
        params.put(APIUtils.TENDER_ID, oldTender.getTenderId());
        params.put(APIUtils.USER_ID, oldTender.getTenderId());
        params.put(APIUtils.TITLE, newTender.getTitle());
        params.put(APIUtils.VALIDITY_PERIOD, String.valueOf(newTender.getValidityPeriod()));
        params.put(APIUtils.STARTING_PRICE, String.valueOf(newTender.getStartingPrice()));
        params.put(APIUtils.IMAGE_RESOURCE, newTender.getImageResource());
        APIHelper.post(APIUtils.BASE_URL+APIUtils.TENDER_END_POINT, listener, errorListener, params);
    }

    public void deleteTender(Tender tender ,Response.Listener<String> listener, Response.ErrorListener errorListener){
        Map<String,String> params = new HashMap<>();
        params.put(APIUtils.METHOD , APIUtils.METHOD_DELETE);
        params.put(APIUtils.TENDER_ID, tender.getTenderId());
        params.put(APIUtils.USER_ID, tender.getTenderId());
        APIHelper.post(APIUtils.BASE_URL+APIUtils.TENDER_END_POINT, listener, errorListener, params);
    }

}