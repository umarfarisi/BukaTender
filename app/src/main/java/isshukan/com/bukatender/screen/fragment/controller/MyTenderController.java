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
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.TenderDetailActivity;
import isshukan.com.bukatender.screen.fragment.MyTenderFragment;
import isshukan.com.bukatender.support.utils.APIUtils;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class MyTenderController{

    private MyTenderFragment fragment;

    private List<Tender> tenders;

    private TenderDA tenderDA;

    public MyTenderController(MyTenderFragment fragment) {
        this.fragment = fragment;
        loadData();
    }

    private void loadData(){
        tenderDA = new TenderDA();
        //TODO change userId
        String userId = "123";
        tenderDA.getUserTender(userId, new DACallback<List<Tender>>() {
            @Override
            public void onSuccess(List<Tender> tenders) {
                MyTenderController.this.tenders = tenders;
                fragment.configureRecyclerView(tenders);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(fragment.getContext(),message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onTenderChoose(int position) {
        Tender tender = tenders.get(position);
        Intent intent = new Intent(fragment.getActivity(), TenderDetailActivity.class);
        intent.putExtra(Constant.TENDER, tender);
        fragment.startActivity(intent);
    }

}
