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
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.constant.ConstantAPI;

/**
 * @author Muhammad Umar Farisi
 * @created 22/05/2017
 */
public class TenderDA {

    private String url;

    public TenderDA(){
        url = ConstantAPI.BASE_URL+ ConstantAPI.TENDER_END_POINT;
    }

    public void createTender(Tender newTender){
        Map<String,String> params = new HashMap<>();
        params.put(ConstantAPI.METHOD , ConstantAPI.METHOD_CREATE);
        params.put(ConstantAPI.TENDER_ID, String.valueOf(newTender.getTenderId()));
        params.put(ConstantAPI.USER_ID, newTender.getUserId());
        params.put(ConstantAPI.TITLE, newTender.getTitle());
        params.put(ConstantAPI.VALIDITY_PERIOD, String.valueOf(newTender.getValidityPeriod()));
        params.put(ConstantAPI.STARTING_PRICE, String.valueOf(newTender.getStartingPrice()));
        params.put(ConstantAPI.IMAGE_RESOURCE, newTender.getImageResource());
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //TODO
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO
            }
        }, params);
    }

    public void getAllTender(final DACallback<List<Tender>> callback){
        Map<String,String> params = new HashMap<>();
        params.put(ConstantAPI.METHOD , ConstantAPI.METHOD_READ);
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Tender> tenders = new ArrayList<>();
                try {
                    JSONObject responseJO = new JSONObject(response);
                    String status =responseJO.getString(ConstantAPI.STATUS);
                    if(status.equals(ConstantAPI.STATUS_SUCCESS)){
                        parserData(responseJO.getJSONArray(ConstantAPI.DATA), tenders);
                        callback.onSuccess(tenders);
                    }else{
                        callback.onFailure("ERROR: STATUC FAILED");
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
        }, params);
    }

    public void getUserTender(String userId, final DACallback<List<Tender>> callback){
        Map<String,String> params = new HashMap<>();
        params.put(ConstantAPI.METHOD , ConstantAPI.METHOD_READ);
        params.put(ConstantAPI.USER_ID, userId);
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Tender> tenders = new ArrayList<>();
                try {
                    JSONObject responseJO = new JSONObject(response);
                    String status =responseJO.getString(ConstantAPI.STATUS);
                    if(status.equals(ConstantAPI.STATUS_SUCCESS)){
                        parserData(responseJO.getJSONArray(ConstantAPI.DATA), tenders);
                        callback.onSuccess(tenders);
                    }else{
                        callback.onFailure("ERROR: STATUC FAILED");
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
        }, params);
    }

    public void updateTender(Tender oldTender, Tender newTender){
        Map<String,String> params = new HashMap<>();
        params.put(ConstantAPI.METHOD , ConstantAPI.METHOD_UPDATE);
        params.put(ConstantAPI.TENDER_ID, String.valueOf(oldTender.getTenderId()));
        params.put(ConstantAPI.USER_ID, oldTender.getUserId());
        params.put(ConstantAPI.TITLE, newTender.getTitle());
        params.put(ConstantAPI.VALIDITY_PERIOD, String.valueOf(newTender.getValidityPeriod()));
        params.put(ConstantAPI.STARTING_PRICE, String.valueOf(newTender.getStartingPrice()));
        params.put(ConstantAPI.IMAGE_RESOURCE, newTender.getImageResource());
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //TODO
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO
            }
        }, params);
    }

    public void deleteTender(Tender tender){
        Map<String,String> params = new HashMap<>();
        params.put(ConstantAPI.METHOD , ConstantAPI.METHOD_DELETE);
        params.put(ConstantAPI.TENDER_ID, String.valueOf(tender.getTenderId()));
        params.put(ConstantAPI.USER_ID, tender.getUserId());
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //TODO
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO
            }
        }, params);
    }

    private void parserData(JSONArray dataJA, List<Tender> tenders) throws JSONException{
        for(int i = 0 ; i < dataJA.length() ; i++){
            JSONObject dataJO = dataJA.getJSONObject(i);
            int tenderId = dataJO.getInt(ConstantAPI.TENDER_ID);
            String userId = dataJO.getString(ConstantAPI.USER_ID);
            String title = dataJO.getString(ConstantAPI.TITLE);
            long validityPeriod = dataJO.getLong(ConstantAPI.VALIDITY_PERIOD);
            int startingPrice = dataJO.getInt(ConstantAPI.STARTING_PRICE);
            String imageResource = dataJO.getString(ConstantAPI.IMAGE_RESOURCE);
            tenders.add(new Tender(tenderId,userId,title,validityPeriod,startingPrice,imageResource));
        }
    }

}
