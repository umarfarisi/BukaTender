package isshukan.com.bukatender.screen.activity.controller;

import android.content.Intent;

import java.util.Date;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.ListBidActivity;
import isshukan.com.bukatender.screen.activity.TenderDetailActivity;

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
        activity.getValidityPeriodTextView().setText(new Date(tender.getValidityPeriod()).toString());
        activity.getTagTextView().setText(tender.getTag().toString());
        activity.getStartingPriceTextView().setText("Rp "+tender.getStartingPrice());

    }

    private void handleIntent() {
        Intent intent = activity.getIntent();
        tender = (Tender) intent.getSerializableExtra(Constant.TENDER);
    }

    public void onClick(int id) {
        switch (id){
            case R.id.listBidButton:
                Intent intent = new Intent(activity, ListBidActivity.class);
                intent.putExtra(Constant.TENDER_ID, tender.getTenderId());
                intent.putExtra(Constant.USER_ID, tender.getUserId());
                activity.startActivity(intent);
                break;
            case R.id.actionFloatingActionButton:
                //TODO
                break;
        }
    }
}
