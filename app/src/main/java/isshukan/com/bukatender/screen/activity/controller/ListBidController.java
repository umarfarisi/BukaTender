package isshukan.com.bukatender.screen.activity.controller;

import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    public ListBidController(ListBidActivity activity) {
        this.activity = activity;
        loadIntent();
        loadData();
        activity.getSupportActionBar().setTitle(tender.getTitle());
    }

    private void loadIntent() {
        Intent intent = activity.getIntent();
        tender = (Tender) intent.getSerializableExtra(Constant.TENDER_ID);
    }

    private void loadData() {
        bidDA = new BidDA();
        bidDA.getTenderBid(tender.getTenderId(), new DACallback<List<Bid>>() {
            @Override
            public void onSuccess(List<Bid> bids) {
                ListBidController.this.bids = bids;
                activity.configureRecyclerView(bids);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
