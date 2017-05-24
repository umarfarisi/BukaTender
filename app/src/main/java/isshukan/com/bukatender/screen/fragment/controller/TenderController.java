package isshukan.com.bukatender.screen.fragment.controller;

import android.content.Intent;
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
import isshukan.com.bukatender.dataaccess.api.TenderDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.TenderDetailActivity;
import isshukan.com.bukatender.screen.fragment.TenderFragment;
import isshukan.com.bukatender.support.utils.APIUtils;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class TenderController{
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
        tenderDA.getAllTender(new DACallback<List<Tender>>() {
            @Override
            public void onSuccess(List<Tender> tenders) {
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
