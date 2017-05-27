package isshukan.com.bukatender.screen.activity.controller;

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

import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.dataaccess.api.APIHelper;
import isshukan.com.bukatender.model.Transaction;
import isshukan.com.bukatender.screen.activity.TransactionListActivity;
import isshukan.com.bukatender.support.utils.Authentication;

/**
 * Created by wirabdillah on 27/05/17.
 */

public class TransactionListController {
    private TransactionListActivity activity;
    private List<Transaction> transactionList;

    public TransactionListController(TransactionListActivity activity){
        this.activity = activity;
        activity.getSupportActionBar().setTitle("Transaksi");
        fetchTransactionList();
    }

    private void fetchTransactionList() {
        String userId = Authentication.getUserId();
        String token = Authentication.getUserToken();

        Map<String, String> header = new HashMap<>();
        String credentials = userId + ":" + token;
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        header.put("Authorization", auth);

        Map<String, String> param = new HashMap<>();
        param.put("page", "1");

        APIHelper.get(ConstantAPI.BUKALAPAK_TRANSACTION_ENDPOINT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject res = new JSONObject(response);
                    JSONArray transactionListJSON = res.getJSONArray("transactions");
                    if (transactionListJSON.length() != 0){
                        List<Transaction> tmpTransactionList = new ArrayList<Transaction>();
                        JSONObject transactionJSON;

                        for (int i = 0; i < transactionListJSON.length(); i++){
                            transactionJSON = transactionListJSON.getJSONObject(i);
                            tmpTransactionList.add(new Transaction(transactionJSON));
                        }

                        transactionList = tmpTransactionList;
                        activity.configureRecylerView(transactionList);
                    } else {
                        Toast.makeText(activity, "You don't have any transactions", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(activity, "Cannot fetch data\nPlease check your network connection", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "Cannot fetch data\nPlease check your network connection", Toast.LENGTH_SHORT).show();
            }
        }, param, header);
    }
}
