package isshukan.com.bukatender.screen.activity.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.dataaccess.api.BidDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.ListBidActivity;

/**
 * @author Muhammad Umar Farisi
 * @created 24/05/2017
 */
public class ListBidController {

    private ListBidActivity activity;

    private BidDA bidDA;
    private Tender tender;
    private List<Bid> bids;
    private Bid deletedBid;

    public ListBidController(ListBidActivity activity) {
        this.activity = activity;
        loadIntent();
        loadData();
        activity.getSupportActionBar().setTitle(tender.getTitle());
    }

    private void loadIntent() {
        Intent intent = activity.getIntent();
        tender = (Tender) intent.getSerializableExtra(Constant.TENDER);
    }

    private void loadData() {
        activity.getProgressBar().setVisibility(View.VISIBLE);
        activity.getBidRecyclerView().setVisibility(View.GONE);
        bidDA = new BidDA();
        bidDA.getTenderBid(tender.getTenderId(), new DACallback<List<Bid>>() {
            @Override
            public void onSuccess(List<Bid> bids) {
                if(isActivityNotNull()) {
                    ListBidController.this.bids = bids;
                    activity.configureRecyclerView(bids);
                    if (bids.isEmpty()) {
                        activity.getEmptyTextView().setVisibility(View.VISIBLE);
                    } else {
                        activity.getEmptyTextView().setVisibility(View.GONE);
                        activity.getBidRecyclerView().setVisibility(View.VISIBLE);
                    }
                    activity.getProgressBar().setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String message) {
                if(isActivityNotNull()){
                    Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
                    if (bids == null || bids.isEmpty()) {
                        activity.getEmptyTextView().setVisibility(View.VISIBLE);
                    } else {
                        activity.getEmptyTextView().setVisibility(View.GONE);
                        activity.getBidRecyclerView().setVisibility(View.VISIBLE);
                    }
                    activity.getProgressBar().setVisibility(View.GONE);
                }
            }
        });
    }

    private boolean isActivityNotNull(){
        return activity != null;
    }

    public void onBidChooseForLongTime(int position) {
        deletedBid = bids.get(position);
        activity.getDialog().show();
    }

    public void onClick(int id) {
        if(id == R.id.deleteButton && deletedBid != null){
            bidDA.deleteBid(deletedBid, new DACallback<Boolean>() {
                @Override
                public void onSuccess(Boolean isSuccess) {
                    if(isActivityNotNull()){
                        if(isSuccess){
                            bids.remove(deletedBid);
                            activity.getAdapter().notifyDataSetChanged();
                            if(bids.isEmpty()){
                                activity.getEmptyTextView().setVisibility(View.VISIBLE);
                            }
                        }else{
                            Toast.makeText(activity,"ERROR: failed to delete bid",Toast.LENGTH_SHORT).show();
                        }
                        activity.getDialog().dismiss();
                    }
                }

                @Override
                public void onFailure(String message) {
                    if(isActivityNotNull()){
                        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
                        activity.getDialog().dismiss();
                    }
                }
            });
        }
    }
}
