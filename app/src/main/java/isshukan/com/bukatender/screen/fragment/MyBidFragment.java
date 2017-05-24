package isshukan.com.bukatender.screen.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.screen.BaseScreen;
import isshukan.com.bukatender.screen.fragment.controller.MyBidController;
import isshukan.com.bukatender.support.adapter.BidAdapter;
import isshukan.com.bukatender.support.adapter.viewholder.listener.BidListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBidFragment extends BaseFragment{

    private View view;
    private RecyclerView bidRecyclerView;

    private BidAdapter adapter;

    private MyBidController controller;

    private BidListener listener = new BidListener() {
        @Override
        public void onBidChoose(int position) {
            if(isControllerNotNull()){
                //TODO
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_bid, container, false);
        return view;
    }

    @Override
    public void loadViews() {
        bidRecyclerView = (RecyclerView) view.findViewById(R.id.bidRecyclerView);
    }

    @Override
    public void setDefaultSetting() {
        controller = new MyBidController(this);
    }

    public void configureRecyclerView(List<Bid> bids){
        adapter = new BidAdapter(bids, getContext(), listener);
        bidRecyclerView.setAdapter(adapter);
        bidRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    public BidAdapter getAdapter() {
        return adapter;
    }

    @Override
    public boolean isControllerNotNull() {
        return bidRecyclerView != null;
    }
}
