package isshukan.com.bukatender.support.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.support.adapter.viewholder.listener.TenderListener;
import isshukan.com.bukatender.support.utils.Formatter;

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
        if(tender.getImageResource() != null && !tender.getImageResource().isEmpty()){
            Picasso.with(itemView.getContext()).load(ConstantAPI.BASE_URL+ConstantAPI.SLASH+tender.getImageResource()).into(photoImageView);
        }
        titleTextView.setText(tender.getTitle());
        validityPeriodTextView.setText(Formatter.dateFormatter(tender.getValidityPeriod()));
        tagTextView.setText(tender.getTag().toString());
        startingPriceTextView.setText(Formatter.priceFormatter(tender.getStartingPrice()));
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        if(position != -1){
            listener.onTenderChoose(position);
        }
    }
}
