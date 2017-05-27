package isshukan.com.bukatender.support.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Transaction;
import isshukan.com.bukatender.support.adapter.viewholder.TransactionViewHolder;
import isshukan.com.bukatender.support.adapter.viewholder.listener.TransactionListener;

/**
 * Created by wirabdillah on 27/05/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder> {
    private List<Transaction> transactionList;
    private Context context;
    private TransactionListener listener;

    public TransactionAdapter(List<Transaction> transactionList, Context context, TransactionListener listener) {
        this.transactionList = transactionList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.transaction_view_holder, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        holder.setData(transactionList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}
