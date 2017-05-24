package isshukan.com.bukatender.screen.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.controller.ListBidController;
import isshukan.com.bukatender.support.adapter.BidAdapter;
import isshukan.com.bukatender.support.adapter.TenderAdapter;
import isshukan.com.bukatender.support.adapter.viewholder.listener.BidListener;

public class ListBidActivity extends BaseActivity {

    private RecyclerView bidRecyclerView;

    private BidAdapter adapter;

    private BidListener listener = new BidListener() {
        @Override
        public void onBidChoose(int position) {
            //TODO
        }
    };

    private ListBidController controller;

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_list_bid);
        bidRecyclerView = (RecyclerView) findViewById(R.id.bidRecyclerView);
    }

    @Override
    public void setDefaultSetting() {
        controller = new ListBidController(this);
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

    public void configureRecyclerView(List<Bid> bids){
        adapter = new BidAdapter(bids, this, listener);
        bidRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        bidRecyclerView.setAdapter(adapter);
    }

    public BidAdapter getAdapter() {
        return adapter;
    }
}
