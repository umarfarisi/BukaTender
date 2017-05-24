package isshukan.com.bukatender.support.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.support.adapter.viewholder.listener.TenderListener;

/**
 * @author Muhammad Umar Farisi
 * @created 22/05/2017
 */
public class TenderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView photoImageView;
    private TextView titleTextView;
    private TextView validityPeriodTextView;
    private TextView tagTextView;
    private TextView startingPriceTextView;

    private TenderListener listener;

    private int position;

    public TenderViewHolder(View itemView, TenderListener listener) {
        super(itemView);
        this.listener = listener;
        this.position = -1;
        itemView.setOnClickListener(this);
        loadViews();
    }

    private void loadViews() {
        photoImageView = (ImageView) itemView.findViewById(R.id.photoImageView);
        titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
        validityPeriodTextView = (TextView) itemView.findViewById(R.id.validityPeriodTextView);
        tagTextView = (TextView) itemView.findViewById(R.id.tagTextView);
        startingPriceTextView = (TextView) itemView.findViewById(R.id.startingPriceTextView);
    }

    public void setData(Tender tender, int position){
        Picasso.with(itemView.getContext()).load(tender.getImageResource()).into(photoImageView);
        titleTextView.setText(tender.getTitle());
        validityPeriodTextView.setText(new Date(tender.getValidityPeriod()).toString());
        tagTextView.setText(tender.getTag().toString());
        startingPriceTextView.setText("RP "+tender.getStartingPrice());
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        if(position != -1){
            listener.onTenderChoose(position);
        }
    }
}
