package isshukan.com.bukatender.screen.activity.controller;

import android.app.Activity;
import android.content.Intent;
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

import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.dataaccess.api.APIHelper;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.screen.activity.ListMylapakActivity;
import isshukan.com.bukatender.screen.activity.MylapakActivity;
import isshukan.com.bukatender.support.utils.Authentication;

/**
 * Created by - on 25/05/2017.
 */

public class ListMylapakController {

    private static final int PRODUCT_DETAIL_REQ_CODE = 1;
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
                        JSONObject myLapakJSON;

                        for(int i = 0; i < mylapakArr.length(); i++) {
                            myLapakJSON = mylapakArr.getJSONObject(i);
                            mylapaksTMP.add(new Mylapak(myLapakJSON));
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

    public void onMylapakChoose(int position) {
        Intent intent = new Intent();
        intent.setClass(activity, MylapakActivity.class);
        intent.putExtra(Constant.PRODUCT, mylapaks.get(position));
        intent.putExtra(Constant.PURPOSE, Constant.PURPOSE_ADD_BID);
        activity.startActivityForResult(intent, PRODUCT_DETAIL_REQ_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == PRODUCT_DETAIL_REQ_CODE){
                activity.setResult(Activity.RESULT_OK, data);
                activity.finish();
            }
        }
    }
}
