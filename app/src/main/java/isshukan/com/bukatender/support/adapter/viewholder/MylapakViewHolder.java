package isshukan.com.bukatender.support.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.support.adapter.viewholder.listener.MylapakListener;
import isshukan.com.bukatender.support.utils.Formatter;

/**
 * Created by - on 25/05/2017.
 */

public class MylapakViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ImageView mylapakImageView;
    private TextView mylapakTitleTextView;
    private TextView mylapakPriceTextView;
    private TextView mylapakDescriptionTextView;

    private MylapakListener listener;

    private int position;

    public MylapakViewHolder(View itemView, MylapakListener listener) {
        super(itemView);
        this.listener = listener;
        this.position = -1;
        itemView.setOnClickListener(this);
        loadViews();
    }

    private void loadViews() {
        mylapakImageView = (ImageView) itemView.findViewById(R.id.mylapakImageView);
        mylapakTitleTextView = (TextView) itemView.findViewById(R.id.mylapakTitleTextView);
        mylapakPriceTextView = (TextView) itemView.findViewById(R.id.mylapakPriceTextView);
        mylapakDescriptionTextView = (TextView) itemView.findViewById(R.id.mylapakDescriptionTextView);
    }

    public void setData(Mylapak mylapak, int position){
        if(mylapak.getImageSmallURL() != null && !mylapak.getImageSmallURL().isEmpty()){
            Picasso.with(itemView.getContext()).load(mylapak.getImageSmallURL()).into(mylapakImageView);
        }
        mylapakTitleTextView.setText(mylapak.getTitle());
        mylapakPriceTextView.setText(Formatter.priceFormatter(mylapak.getPrice()));
        mylapakDescriptionTextView.setText(mylapak.getDescription());
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        if(position != -1){
            listener.onMylapakChoose(position);
        }
    }
}
