package isshukan.com.bukatender.screen.activity.controller;

import android.util.Base64;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.dataaccess.api.APIHelper;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.screen.activity.ListMylapakActivity;
import isshukan.com.bukatender.support.utils.Authentication;

/**
 * Created by - on 25/05/2017.
 */

public class ListMylapakController {

    private ListMylapakActivity activity;
    private List<Mylapak> mylapaks;

    public ListMylapakController(ListMylapakActivity activity) {
        this.activity = activity;
        activity.getSupportActionBar().setTitle("MyLapak");
        fetchMylapak();
    }

    public void fetchMylapak() {
        String user_id = Authentication.getUserId();
        String token = Authentication.getUserToken();

        Map<String, String> header = new HashMap<>();
        String credentials = user_id + ":" + token;
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        header.put("Authorization", auth);

        APIHelper.get(ConstantAPI.MYLAPAK_ENDPOINT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray mylapakArr = jsonObject.getJSONArray("products");
                    if(mylapakArr.length() != 0) {
                        List<Mylapak> mylapaksTMP = new ArrayList<Mylapak>();
                        JSONObject mylapak;
                        String mylapakId;
                        String title;
                        Double price;
                        String category;
                        String description;
                        String imageSmallURL;
                        String imageURL;
                        String condition;
                        int stock;
                        double avg_rate;
                        int user_count_rate;
                        int view_count;
                        int interest_count;

                        for(int i = 0; i < mylapakArr.length(); i++) {
                            mylapak = mylapakArr.getJSONObject(i);
                            mylapakId = mylapak.getString("id");
                            title = mylapak.getString("name");
                            price = mylapak.getDouble("price");
                            category = mylapak.getString("category");
                            description = mylapak.getString("desc");
                            imageSmallURL = mylapak.getJSONArray("small_images").getString(0);
                            imageURL = mylapak.getJSONArray("images").getString(0);
                            condition = mylapak.getString("condition");
                            stock = mylapak.optInt("stock");
                            avg_rate = mylapak.getJSONObject("rating").getDouble("average_rate");
                            user_count_rate = mylapak.getJSONObject("rating").getInt("user_count");
                            view_count = mylapak.getInt("view_count");
                            interest_count = mylapak.getInt("interest_count");

                            mylapaksTMP.add(new Mylapak(mylapakId, title, price, category, description, imageSmallURL, imageURL,
                                    condition, stock, avg_rate, user_count_rate, view_count, interest_count));
                        }

                        ListMylapakController.this.mylapaks = mylapaksTMP;
                        activity.configureRecyclerView(mylapaks);
                    } else {
                        Toast.makeText(activity, "You didn't have any product", Toast.LENGTH_SHORT).show();
                    }
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
