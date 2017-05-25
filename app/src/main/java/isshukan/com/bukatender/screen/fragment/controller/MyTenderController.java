package isshukan.com.bukatender.screen.fragment.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.dataaccess.api.TenderDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.TenderDetailActivity;
import isshukan.com.bukatender.screen.fragment.MyTenderFragment;
import isshukan.com.bukatender.support.utils.Authentication;

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
        tenderDA = new TenderDA();
    }

    public void loadData() {
        fragment.getProgressBar().setVisibility(View.VISIBLE);
        tenderDA.getAllTender(new DACallback<List<Tender>>() {
            @Override
            public void onSuccess(List<Tender> tenders) {
                MyTenderController.this.tenders = tenders;
                fragment.configureRecyclerView(tenders);
                if(tenders.isEmpty()){
                    fragment.getEmptyTextView().setVisibility(View.VISIBLE);
                }else {
                    fragment.getEmptyTextView().setVisibility(View.GONE);
                }
                fragment.getProgressBar().setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(fragment.getContext(),message,Toast.LENGTH_SHORT).show();
                if(tenders == null || tenders.isEmpty()){
                    fragment.getEmptyTextView().setVisibility(View.VISIBLE);
                }else {
                    fragment.getEmptyTextView().setVisibility(View.GONE);
                }
                fragment.getProgressBar().setVisibility(View.GONE);
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
