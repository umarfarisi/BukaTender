package isshukan.com.bukatender.support.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.support.adapter.viewholder.listener.BidListener;
import isshukan.com.bukatender.support.utils.Formatter;

/**
 * @author Muhammad Umar Farisi
 * @created 24/05/2017
 */
public class BidViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView photoImageView;
    private TextView titleTextView;
    private TextView priceTextView;
    private TextView shortDescriptionTextView;

    private BidListener listener;

    private int position;

    public BidViewHolder(View itemView, BidListener listener) {
        super(itemView);
        this.listener = listener;
        this.position = -1;
        itemView.setOnClickListener(this);
        loadViews();
    }

    private void loadViews() {
        photoImageView = (ImageView) itemView.findViewById(R.id.photoImageView);
        titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
        priceTextView = (TextView) itemView.findViewById(R.id.priceTextView);
        shortDescriptionTextView = (TextView) itemView.findViewById(R.id.shortDescriptionTextView);
    }

    public void setData(Bid bid , int position){
        Picasso.with(itemView.getContext()).load(bid.getImageResource()).into(photoImageView);
        titleTextView.setText(bid.getTitleProduct());
        priceTextView.setText(Formatter.priceFormatter(bid.getBidPrice()));
        shortDescriptionTextView.setText(bid.getShortDescription());
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        if(position != -1){
            listener.onBidChoose(position);
        }
    }
}
