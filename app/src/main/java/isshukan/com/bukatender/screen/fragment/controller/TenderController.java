package isshukan.com.bukatender.screen.fragment.controller;

import android.util.Log;
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

import isshukan.com.bukatender.dataaccess.TenderDA;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.fragment.TenderFragment;
import isshukan.com.bukatender.support.utils.APIUtils;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class TenderController implements Response.Listener<String>, Response.ErrorListener {

    private TenderFragment fragment;
    private TenderDA tenderDA;

    private List<Tender> tenders;

    public TenderController(TenderFragment fragment) {
        this.fragment = fragment;
        loadData();
    }

    private void loadData() {
        tenders = new ArrayList<>();
        tenderDA = new TenderDA();
        Map<String,String> params = new HashMap<>();
        params.put(APIUtils.METHOD , APIUtils.METHOD_READ);
        tenderDA.getAll(this, this, params);
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject responseJO = new JSONObject(response);
            String status =responseJO.getString(APIUtils.STATUS);
            if(status.equals(APIUtils.STATUS_SUCCESS)){
                parserData(responseJO.getJSONArray(APIUtils.DATA));
            }
        } catch (JSONException e) {

        }finally {
            fragment.configureRecyclerView(tenders);
        }
    }

    private void parserData(JSONArray dataJA) throws JSONException{
        for(int i = 0 ; i < dataJA.length() ; i++){
            JSONObject dataJO = dataJA.getJSONObject(i);
            String tenderId = dataJO.getString(APIUtils.TENDER_ID);
            String userId = dataJO.getString(APIUtils.USER_ID);
            String title = dataJO.getString(APIUtils.TITLE);
            long validityPeriod = dataJO.getLong(APIUtils.VALIDITY_PERIOD);
            int startingPrice = dataJO.getInt(APIUtils.STARTING_PRICE);
            String imageResource = dataJO.getString(APIUtils.IMAGE_RESOURCE);
            tenders.add(new Tender(tenderId,userId,title,validityPeriod,startingPrice,imageResource));
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(fragment.getContext(),"Error: "+error.getMessage(),Toast.LENGTH_SHORT).show();
    }

}
