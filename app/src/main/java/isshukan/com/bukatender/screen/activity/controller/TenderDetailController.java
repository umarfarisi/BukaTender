package isshukan.com.bukatender.screen.activity.controller;

import android.content.Intent;

import java.util.Date;

import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.model.Tender;
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
}
