package isshukan.com.bukatender.screen.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Transaction;
import isshukan.com.bukatender.screen.activity.controller.TransactionListController;
import isshukan.com.bukatender.support.adapter.TransactionAdapter;
import isshukan.com.bukatender.support.adapter.viewholder.listener.TransactionListener;

public class TransactionListActivity extends BaseActivity {

    private TransactionListController controller;
    private RecyclerView transactionListRecyclerView;
    private TransactionAdapter adapter;
    private TransactionListener listener = new TransactionListener() {
        @Override
        public void onTransactionChoose(int position, Transaction transaction) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), TransactionActivity.class);
            intent.putExtra("transactionlist-object", transaction);
            startActivity(intent);
        }
    };

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_transaction_list);
        transactionListRecyclerView = (RecyclerView) findViewById(R.id.transactionListRecyclerView);
    }

    @Override
    public void setDefaultSetting() {
        controller = new TransactionListController(this);
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

    public void configureRecylerView(List<Transaction> transactionList) {
        adapter = new TransactionAdapter(transactionList, this, listener);
        transactionListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        transactionListRecyclerView.setAdapter(adapter);
    }

    public TransactionAdapter getAdapter(){
        return adapter;
    }
}
