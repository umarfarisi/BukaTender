package isshukan.com.bukatender.support.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.model.Transaction;
import isshukan.com.bukatender.support.adapter.viewholder.listener.TransactionListener;
import isshukan.com.bukatender.support.utils.Formatter;

/**
 * Created by wirabdillah on 27/05/17.
 */

public class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView transactionImageView;
    private TextView transactionTitleTextView;
    private TextView transactionPriceTextView;
    private TextView transactionStatusTextView;

    private Transaction transaction;
    private TransactionListener listener;
    private int position;

    public TransactionViewHolder(View itemView, TransactionListener listener) {
        super(itemView);
        this.listener = listener;
        this.position = -1;
        itemView.setOnClickListener(this);
        loadViews();
    }

    public void loadViews() {
        transactionImageView = (ImageView) itemView.findViewById(R.id.transactionImageView);
        transactionTitleTextView = (TextView) itemView.findViewById(R.id.transactionTitleTextView);
        transactionPriceTextView = (TextView) itemView.findViewById(R.id.transactionPriceTextView);
        transactionStatusTextView = (TextView) itemView.findViewById(R.id.transactionStatusTextView);
    }

    public void setData(Transaction transaction, int position) {
        Mylapak firstProduct = transaction.getProducts().get(0);
        if (firstProduct.getImageSmallURL() != null && !firstProduct.getImageSmallURL().isEmpty()){
            Picasso.with(itemView.getContext()).load(firstProduct.getImageSmallURL()).into(transactionImageView);
        }
        transactionTitleTextView.setText(firstProduct.getTitle());
        transactionPriceTextView.setText(Formatter.priceFormatter(firstProduct.getPrice()));
        transactionStatusTextView.setText("Status: " + transaction.getState());
        this.position = position;
        this.transaction = transaction;
    }

    @Override
    public void onClick(View v) {
        if (position != -1){
            listener.onTransactionChoose(position, transaction);
        }
    }
}
