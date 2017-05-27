package isshukan.com.bukatender.dataaccess.api;

import android.util.Base64;
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

import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.support.utils.Authentication;

/**
 * @author Muhammad Umar Farisi
 * @created 28/05/2017
 */
public class MylapakDA  {

    public void getAllMylapakUser(String userId, String token, final DACallback<List<Mylapak>> callback){
        Map<String, String> header = new HashMap<>();
        String credentials = userId + ":" + token;
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        header.put(ConstantAPI.AUTHORIZATION, auth);

        APIHelper.get(ConstantAPI.MYLAPAK_ENDPOINT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray mylapakArr = jsonObject.getJSONArray(ConstantAPI.PRODUCTS);
                    List<Mylapak> mylapaks = new ArrayList<>();
                    if(mylapakArr.length() != 0) {
                        JSONObject myLapakJSON;
                        for(int i = 0; i < mylapakArr.length(); i++) {
                            myLapakJSON = mylapakArr.getJSONObject(i);
                            mylapaks.add(parserMylapak(myLapakJSON));
                        }
                    }
                    callback.onSuccess(mylapaks);
                } catch (Exception e) {
                    callback.onFailure("ERROR: "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure("ERROR: "+error.getMessage());
            }
        }, header);
    }

    public void getSpecificMylapak(String productID, String userId, final DACallback<Mylapak> callback){
        String token = Authentication.getUserToken();
        String auth = "Basic " + Base64.encodeToString((userId + ":" + token).getBytes(), Base64.NO_WRAP);
        Map<String, String> header = new HashMap<>();
        header.put(ConstantAPI.AUTHORIZATION, auth);
        header.put("Content-Type", "application/json");
        APIHelper.get(ConstantAPI.BUKALAPAK_PRODUCT_ENDPOINT + productID + ".json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    Mylapak mylapak = parserMylapak(jsonObject.getJSONObject(ConstantAPI.PRODUCT));
                    callback.onSuccess(mylapak);
                } catch (JSONException e) {
                    callback.onFailure("ERROR: "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure("ERRORRR: "+error.getMessage());
            }
        }, header);
    }

    private Mylapak parserMylapak(JSONObject responseJO) throws JSONException{
        String mylapakId = responseJO.getString("id");
        String title = responseJO.getString("name");
        double price = responseJO.getDouble("price");
        String category = responseJO.getString("category");
        String description = responseJO.getString("desc");
        String imageSmallURL = responseJO.getJSONArray("small_images").getString(0);
        String imageURL = responseJO.getJSONArray("images").getString(0);
        String condition = responseJO.getString("condition");
        int stock = responseJO.optInt("stock");
        double avg_rate = responseJO.getJSONObject("rating").getDouble("average_rate");
        int userCountRate = responseJO.getJSONObject("rating").getInt("user_count");
        int view_count = responseJO.getInt("view_count");
        int interest_count = responseJO.getInt("interest_count");
        String seller = responseJO.getString("seller_name");
        int sellerPositiveFeedback = responseJO.optInt("seller_positive_feedback");
        int sellerNegativeFeedback = responseJO.optInt("seller_negative_feedback");
        return new Mylapak(mylapakId,title, price, category, description, imageSmallURL, imageURL, condition, seller, sellerPositiveFeedback, sellerNegativeFeedback, stock, avg_rate, userCountRate, view_count, interest_count);
    }

}
