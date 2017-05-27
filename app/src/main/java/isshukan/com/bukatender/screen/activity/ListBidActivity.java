package isshukan.com.bukatender.screen.activity;

import android.app.Dialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.controller.ListBidController;
import isshukan.com.bukatender.support.adapter.BidAdapter;
import isshukan.com.bukatender.support.adapter.TenderAdapter;
import isshukan.com.bukatender.support.adapter.viewholder.listener.BidListener;

public class ListBidActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView bidRecyclerView;
    private ProgressBar progressBar;
    private TextView emptyTextView;

    private Dialog dialog;
    private Button deleteButton;

    private BidAdapter adapter;

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

    private ListBidController controller;

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_list_bid);
        bidRecyclerView = (RecyclerView) findViewById(R.id.bidRecyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        emptyTextView = (TextView) findViewById(R.id.emptyTextView);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bid_context_dialog);
        deleteButton = (Button) dialog.findViewById(R.id.deleteButton);

    }

    @Override
    public void setDefaultSetting() {
        controller = new ListBidController(this);
        deleteButton.setOnClickListener(this);
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

    public Dialog getDialog() {
        return dialog;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public TextView getEmptyTextView() {
        return emptyTextView;
    }

    public BidAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView getBidRecyclerView() {
        return bidRecyclerView;
    }

    @Override
    public void onClick(View v) {
        if(isControllerNotNull()){
            controller.onClick(v.getId());
        }
    }
}
