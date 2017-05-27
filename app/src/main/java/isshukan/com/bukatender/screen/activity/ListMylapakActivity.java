package isshukan.com.bukatender.screen.activity;

/**
 * Created by - on 25/05/2017.
 */

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.screen.activity.controller.ListMylapakController;
import isshukan.com.bukatender.support.adapter.MylapakAdapter;
import isshukan.com.bukatender.support.adapter.viewholder.listener.MylapakListener;

public class ListMylapakActivity extends BaseActivity {

    private RecyclerView mylapakRecyclerView;
    private ProgressBar progressBar;
    private TextView emptyTextView;

    private MylapakAdapter adapter;

    private MylapakListener listener = new MylapakListener() {
        @Override
        public void onMylapakChoose(int position) {
            if(isControllerNotNull()){
                controller.onMylapakChoose(position);
            }
        }
    };

    private ListMylapakController controller;

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_list_mylapak);
        mylapakRecyclerView = (RecyclerView) findViewById(R.id.mylapakRecyclerView);
        emptyTextView = (TextView) findViewById(R.id.emptyTextView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void setDefaultSetting() {
        controller = new ListMylapakController(this);
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

    public void configureRecyclerView(List<Mylapak> mylapaks){
        adapter = new MylapakAdapter(mylapaks, this, listener);
        mylapakRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mylapakRecyclerView.setAdapter(adapter);
    }

    public MylapakAdapter getAdapter() {
        return adapter;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public TextView getEmptyTextView() {
        return emptyTextView;
    }

    public RecyclerView getMylapakRecyclerView() {
        return mylapakRecyclerView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(isControllerNotNull()){
            controller.onActivityResult(requestCode, resultCode, data);
        }
    }
}

