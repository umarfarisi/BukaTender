package isshukan.com.bukatender.support.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.support.adapter.viewholder.TenderViewHolder;
import isshukan.com.bukatender.support.adapter.viewholder.listener.TenderListener;

/**
 * @author Muhammad Umar Farisi
 * @created 22/05/2017
 */
public class TenderAdapter extends RecyclerView.Adapter<TenderViewHolder> {

    private List<Tender> tenders;
    private Context context;
    private TenderListener listener;

    public TenderAdapter(List<Tender> tenders, Context context, TenderListener listener) {
        this.tenders = tenders;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public TenderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TenderViewHolder(LayoutInflater.from(context).inflate(R.layout.tender_view_holder, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(TenderViewHolder holder, int position) {
        holder.setData(tenders.get(position),position);
    }

    @Override
    public int getItemCount() {
        return tenders.size();
    }
}
