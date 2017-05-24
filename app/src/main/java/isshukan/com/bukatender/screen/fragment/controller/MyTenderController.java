package isshukan.com.bukatender.screen.fragment.controller;

import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.dataaccess.api.TenderDA;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.TenderDetailActivity;
import isshukan.com.bukatender.screen.fragment.MyTenderFragment;
import isshukan.com.bukatender.support.utils.APIUtils;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class MyTenderController implements Response.Listener<String> , Response.ErrorListener{

    private MyTenderFragment fragment;

    private List<Tender> tenders;

    private TenderDA tenderDA;

    public MyTenderController(MyTenderFragment fragment) {
        this.fragment = fragment;
        loadData();
    }

    private void loadData(){
        tenders = new ArrayList<>();
        tenderDA = new TenderDA();
        //TODO change userId
        String userId = "123";
        tenderDA.getUserTender(userId,this,this);
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
            int tenderId = dataJO.getInt(APIUtils.TENDER_ID);
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

    public void onTenderChoose(int position) {
        Tender tender = tenders.get(position);
        Intent intent = new Intent(fragment.getActivity(), TenderDetailActivity.class);
        intent.putExtra(Constant.TENDER, tender);
        fragment.startActivity(intent);
    }

}
