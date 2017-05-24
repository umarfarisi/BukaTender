package isshukan.com.bukatender.screen.fragment.controller;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import isshukan.com.bukatender.dataaccess.api.BidDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.screen.fragment.MyBidFragment;

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
        loadData();
    }

    private void loadData() {
        bidDA = new BidDA();
        bids = new ArrayList<>();
        bidDA.getUserBid("123", new DACallback<List<Bid>>() {
            @Override
            public void onSuccess(List<Bid> bids) {
                fragment.configureRecyclerView(bids);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(fragment.getContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
