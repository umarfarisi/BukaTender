package isshukan.com.bukatender.screen.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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
public class MyBidFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private RecyclerView bidRecyclerView;
    private ProgressBar progressBar;
    private TextView emptyTextView;

    private Dialog dialog;
    private Button deleteButton;

    private BidAdapter adapter;

    private MyBidController controller;

    private BidListener listener = new BidListener() {
        @Override
        public void onBidChoose(int position) {
            if(isControllerNotNull()){
                controller.onBidChoose(position);
            }
        }

        @Override
        public void onBidChooseForLongTime(int position) {
            if(isControllerNotNull()){
                controller.onBidChooseForLongTime(position);
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
    public void onStart() {
        super.onStart();
        if(isControllerNotNull()){
            controller.loadData();
        }
    }

    @Override
    public void loadViews() {
        bidRecyclerView = (RecyclerView) view.findViewById(R.id.bidRecyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        emptyTextView = (TextView) view.findViewById(R.id.emptyTextView);
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bid_context_dialog);
        deleteButton = (Button) dialog.findViewById(R.id.deleteButton);
    }

    @Override
    public void setDefaultSetting() {
        controller = new MyBidController(this);
        deleteButton.setOnClickListener(this);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public TextView getEmptyTextView() {
        return emptyTextView;
    }

    public RecyclerView getBidRecyclerView() {
        return bidRecyclerView;
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

    @Override
    public void onClick(View v) {
        if(isControllerNotNull()){
            controller.onClick(v.getId());
        }
    }

    public Dialog getDialog() {
        return dialog;
    }
}
