package isshukan.com.bukatender.screen.fragment.controller;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import isshukan.com.bukatender.dataaccess.api.BidDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.screen.fragment.MyBidFragment;
import isshukan.com.bukatender.support.utils.Authentication;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class MyBidController{

    private MyBidFragment fragment;

    private BidDA bidDA;

    private List<Bid> bids;

    public MyBidController(MyBidFragment fragment) {
        this.fragment = fragment;
        bidDA = new BidDA();
    }

    public void loadData() {
        fragment.getProgressBar().setVisibility(View.VISIBLE);
        fragment.getBidRecyclerView().setVisibility(View.GONE);
        bidDA.getUserBid(Authentication.getUserId(), new DACallback<List<Bid>>() {
            @Override
            public void onSuccess(List<Bid> bids) {
                MyBidController.this.bids = bids;
                fragment.configureRecyclerView(bids);
                if(bids.isEmpty()){
                    fragment.getEmptyTextView().setVisibility(View.VISIBLE);
                }else{
                    fragment.getEmptyTextView().setVisibility(View.GONE);
                    fragment.getBidRecyclerView().setVisibility(View.VISIBLE);
                }
                fragment.getProgressBar().setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(fragment.getContext(), message, Toast.LENGTH_LONG).show();
                if(bids == null || bids.isEmpty()){
                    fragment.getEmptyTextView().setVisibility(View.VISIBLE);
                }else{
                    fragment.getEmptyTextView().setVisibility(View.GONE);
                    fragment.getBidRecyclerView().setVisibility(View.VISIBLE);
                }
                fragment.getProgressBar().setVisibility(View.GONE);
            }
        });
    }

}
