package isshukan.com.bukatender.support.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.support.adapter.viewholder.MylapakViewHolder;
import isshukan.com.bukatender.support.adapter.viewholder.listener.MylapakListener;

/**
 * Created by - on 25/05/2017.
 */

public class MylapakAdapter extends RecyclerView.Adapter<MylapakViewHolder> {
    private List<Mylapak> mylapaks;
    private Context context;
    private MylapakListener listener;

    public MylapakAdapter(List<Mylapak> mylapaks, Context context, MylapakListener listener) {
        this.mylapaks = mylapaks;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MylapakViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MylapakViewHolder(LayoutInflater.from(context).inflate(R.layout.mylapak_view_holder, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(MylapakViewHolder holder, int position) {
        holder.setData(mylapaks.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mylapaks.size();
    }
}
