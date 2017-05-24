package isshukan.com.bukatender.support.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.support.adapter.viewholder.BidViewHolder;
import isshukan.com.bukatender.support.adapter.viewholder.listener.BidListener;

/**
 * @author Muhammad Umar Farisi
 * @created 24/05/2017
 */
public class BidAdapter extends RecyclerView.Adapter<BidViewHolder> {

    private List<Bid> bids;
    private Context context;
    private BidListener listener;

    public BidAdapter(List<Bid> bids, Context context, BidListener listener) {
        this.bids = bids;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public BidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BidViewHolder(LayoutInflater.from(context).inflate(R.layout.bid_view_holder,parent,false),listener);
    }

    @Override
    public void onBindViewHolder(BidViewHolder holder, int position) {
        holder.setData(bids.get(position),position);
    }

    @Override
    public int getItemCount() {
        return bids.size();
    }
}
