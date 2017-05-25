package isshukan.com.bukatender.screen.activity.controller;

import android.content.Intent;

import com.squareup.picasso.Picasso;

import java.util.Date;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.ListBidActivity;
import isshukan.com.bukatender.screen.activity.TenderDetailActivity;
import isshukan.com.bukatender.support.utils.Formatter;

/**
 * @author Muhammad Umar Farisi
 * @created 24/05/2017
 */
public class TenderDetailController {

    private TenderDetailActivity activity;

    private Tender tender;

    public TenderDetailController(TenderDetailActivity activity) {
        this.activity = activity;
        handleIntent();
        loadData();
    }

    private void loadData() {
        activity.getSupportActionBar().setTitle(tender.getTitle());
        activity.getShortDescriptionTextView().setText(tender.getShortDescription());
        activity.getValidityPeriodTextView().setText("Expired date : "+Formatter.dateFormatter(tender.getValidityPeriod()));
        activity.getTagTextView().setText("Tag : "+tender.getTag().toString());
        activity.getStartingPriceTextView().setText("Starting price : "+Formatter.priceFormatter(tender.getStartingPrice()));
        Picasso.with(activity).load(tender.getImageResource()).into(activity.getPhotoImageView());
    }

    private void handleIntent() {
        Intent intent = activity.getIntent();
        tender = (Tender) intent.getSerializableExtra(Constant.TENDER);
    }

    public void onClick(int id) {
        switch (id){
            case R.id.listBidButton:
                Intent intent = new Intent(activity, ListBidActivity.class);
                intent.putExtra(Constant.TENDER, tender);
                activity.startActivity(intent);
                break;
            case R.id.actionFloatingActionButton:
                //TODO
                break;
        }
    }
}
