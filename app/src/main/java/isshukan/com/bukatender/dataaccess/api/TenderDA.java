package isshukan.com.bukatender.dataaccess.api;

import android.util.Log;

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

    public void createTender(Tender newTender, final DACallback<Boolean> callback){
        Map<String,String> params = new HashMap<>();
        params.put(ConstantAPI.METHOD , ConstantAPI.METHOD_CREATE);
        params.put(ConstantAPI.USER_ID, newTender.getUserId());
        params.put(ConstantAPI.TITLE, newTender.getTitle());
        params.put(ConstantAPI.VALIDITY_PERIOD, String.valueOf(newTender.getValidityPeriod()));
        params.put(ConstantAPI.STARTING_PRICE, String.valueOf(newTender.getStartingPrice()));
        params.put(ConstantAPI.IMAGE_RESOURCE, newTender.getImageResource());
        params.put(ConstantAPI.SHORT_DESCRIPTION, newTender.getShortDescription());
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseJO = new JSONObject(response);
                    String status = responseJO.getString(ConstantAPI.STATUS);
                    callback.onSuccess(status.equals(ConstantAPI.STATUS_SUCCESS));
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
                        parserListOfTender(responseJO.getJSONArray(ConstantAPI.DATA), tenders);
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
                Log.d("RESPONSEEE","e>> "+error.getMessage());
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
                        parserListOfTender(responseJO.getJSONArray(ConstantAPI.DATA), tenders);
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

    public void updateTender(Tender oldTender, Tender newTender, final DACallback<Boolean> callback){
        Map<String,String> params = new HashMap<>();
        params.put(ConstantAPI.METHOD , ConstantAPI.METHOD_UPDATE);
        params.put(ConstantAPI.TENDER_ID, String.valueOf(oldTender.getTenderId()));
        params.put(ConstantAPI.USER_ID, oldTender.getUserId());
        params.put(ConstantAPI.TITLE, newTender.getTitle());
        params.put(ConstantAPI.VALIDITY_PERIOD, String.valueOf(newTender.getValidityPeriod()));
        params.put(ConstantAPI.STARTING_PRICE, String.valueOf(newTender.getStartingPrice()));
        params.put(ConstantAPI.IMAGE_RESOURCE, newTender.getImageResource());
        params.put(ConstantAPI.SHORT_DESCRIPTION, newTender.getShortDescription());
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSEEUPDATE",">> "+response);
                try {
                    JSONObject responseJO = new JSONObject(response);
                    String status = responseJO.getString(ConstantAPI.STATUS);
                    callback.onSuccess(status.equals(ConstantAPI.STATUS_SUCCESS));
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

    public void deleteTender(Tender tender, final DACallback<Boolean> callback){
        Map<String,String> params = new HashMap<>();
        params.put(ConstantAPI.METHOD , ConstantAPI.METHOD_DELETE);
        params.put(ConstantAPI.TENDER_ID, String.valueOf(tender.getTenderId()));
        params.put(ConstantAPI.USER_ID, tender.getUserId());
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseJO = new JSONObject(response);
                    String status = responseJO.getString(ConstantAPI.STATUS);
                    callback.onSuccess(status.equals(ConstantAPI.STATUS_SUCCESS));
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

    private void parserListOfTender(JSONArray dataJA, List<Tender> tenders) throws JSONException{
        for(int i = 0 ; i < dataJA.length() ; i++){
            JSONObject dataJO = dataJA.getJSONObject(i);
            tenders.add(parserTender(dataJO));
        }
    }

    public void getSpecificTender(int tenderId, String userId, final DACallback<Tender> callback) {
        Map<String,String> params = new HashMap<>();
        params.put(ConstantAPI.METHOD , ConstantAPI.METHOD_READ);
        params.put(ConstantAPI.USER_ID, userId);
        params.put(ConstantAPI.TENDER_ID, String.valueOf(tenderId));
        APIHelper.post(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseJO = new JSONObject(response);
                    String status =responseJO.getString(ConstantAPI.STATUS);
                    if(status.equals(ConstantAPI.STATUS_SUCCESS)){
                        List<Tender> tenders = new ArrayList<>();
                        parserListOfTender(responseJO.getJSONArray(ConstantAPI.DATA), tenders);
                        callback.onSuccess(tenders.get(0));
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

    private Tender parserTender(JSONObject dataJO) throws JSONException{
        int tenderId = dataJO.getInt(ConstantAPI.TENDER_ID);
        String userId = dataJO.getString(ConstantAPI.USER_ID);
        String title = dataJO.getString(ConstantAPI.TITLE);
        long validityPeriod = dataJO.getLong(ConstantAPI.VALIDITY_PERIOD);
        int startingPrice = dataJO.getInt(ConstantAPI.STARTING_PRICE);
        String imageResource = dataJO.getString(ConstantAPI.IMAGE_RESOURCE);
        String shortDescription = dataJO.getString(ConstantAPI.SHORT_DESCRIPTION);
        JSONArray tagJA = dataJO.getJSONArray(ConstantAPI.TAG);
        List<String> tags = new ArrayList<>();
        for(int j = 0 ; j < tagJA.length() ; j++){
            tags.add(tagJA.getString(j));
        }
        return new Tender(tenderId,userId,title,validityPeriod,startingPrice,imageResource,shortDescription,tags);
    }
}
