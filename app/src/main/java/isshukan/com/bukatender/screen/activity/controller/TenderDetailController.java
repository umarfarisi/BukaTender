package isshukan.com.bukatender.screen.activity.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Date;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.dataaccess.api.BidDA;
import isshukan.com.bukatender.dataaccess.api.TenderDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.ListBidActivity;
import isshukan.com.bukatender.screen.activity.ListMylapakActivity;
import isshukan.com.bukatender.screen.activity.SetTenderActivity;
import isshukan.com.bukatender.screen.activity.TenderDetailActivity;
import isshukan.com.bukatender.support.utils.Authentication;
import isshukan.com.bukatender.support.utils.Formatter;

/**
 * @author Muhammad Umar Farisi
 * @created 24/05/2017
 */
public class TenderDetailController {

    private static final int EDIT_TENDER_REQ_CODE = 1;
    private static final int ADD_BID_REQ_CODE = 2;
    private TenderDetailActivity activity;

    private Tender tender;
    private TenderDA tenderDA;
    private BidDA bidDA;

    public TenderDetailController(TenderDetailActivity activity) {
        this.activity = activity;
        tenderDA = new TenderDA();
        bidDA = new BidDA();
        handleIntent();
        loadData();
        setDefaultSetting();
    }

    private void setDefaultSetting() {
        if(tender.getUserId().equals(Authentication.getUserId())){
            //Edit
            activity.getActionFloatingActionButton().setImageResource(R.mipmap.ic_edit);
        }else{
            //Add
            activity.getActionFloatingActionButton().setImageResource(R.mipmap.ic_add);
        }
    }

    private void loadData() {
        if(tender != null && tender.getUserId() != null && tender.getTenderId() != -1) {
            tenderDA.getSpecificTender(tender.getTenderId(), tender.getUserId(), new DACallback<Tender>() {
                @Override
                public void onSuccess(Tender tender) {
                    if(isActivityNotNull()) {
                        activity.getSupportActionBar().setTitle(tender.getTitle());
                        activity.getShortDescriptionTextView().setText(tender.getShortDescription());
                        activity.getValidityPeriodTextView().setText("Expired date : " + Formatter.dateFormatter(tender.getValidityPeriod()));
                        activity.getTagTextView().setText("Tag : " + tender.getTag().toString());
                        activity.getStartingPriceTextView().setText("Starting price : " + Formatter.priceFormatter(tender.getStartingPrice()));
                        if (tender.getImageResource() != null && !tender.getImageResource().isEmpty()) {
                            Picasso.with(activity).load(ConstantAPI.BASE_URL + ConstantAPI.SLASH + tender.getImageResource()).into(activity.getPhotoImageView());
                        }
                    }
                }

                @Override
                public void onFailure(String message) {
                    if(isActivityNotNull()){
                        Toast.makeText(activity, "ERROR: " + message, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void handleIntent() {
        Intent intent = activity.getIntent();
        tender = (Tender) intent.getSerializableExtra(Constant.TENDER);
    }

    public void onClick(int id) {
        Intent intent;
        switch (id){
            case R.id.listBidButton:
                intent = new Intent(activity, ListBidActivity.class);
                intent.putExtra(Constant.TENDER, tender);
                activity.startActivity(intent);
                break;
            case R.id.actionFloatingActionButton:
                boolean isTenderUser = tender.getUserId().equals(Authentication.getUserId());
                if(isTenderUser){
                    //Edit
                    intent = new Intent(activity, SetTenderActivity.class);
                    intent.putExtra(Constant.TENDER, tender);
                    activity.startActivityForResult(intent, EDIT_TENDER_REQ_CODE);
                }else{
                    //Add Bid
                    intent = new Intent(activity, ListMylapakActivity.class);
                    activity.startActivityForResult(intent, ADD_BID_REQ_CODE);
                }
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK) {

            if (requestCode == EDIT_TENDER_REQ_CODE) {
                this.tender = (Tender) data.getSerializableExtra(Constant.TENDER);
                loadData();
            } else if (requestCode == ADD_BID_REQ_CODE) {
                Mylapak mylapak = (Mylapak) data.getSerializableExtra(Constant.PRODUCT);
                Bid bid = new Bid(tender.getTenderId(), mylapak.getMylapakId(), tender.getUserId(), Authentication.getUserId(), mylapak.getImageURL(), mylapak.getTitle(), mylapak.getPrice(), mylapak.getDescription());
                bidDA.createBid(bid, new DACallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean isSuccess) {
                        if(isActivityNotNull()) {
                            if (isSuccess) {
                                Intent intent = new Intent(activity, ListBidActivity.class);
                                intent.putExtra(Constant.TENDER, tender);
                                activity.startActivity(intent);
                            } else {
                                Toast.makeText(activity, "Product had been added to bid this tender", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(String message) {
                        if(isActivityNotNull()){
                            Toast.makeText(activity,"ERROR: "+message,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    public void onOptionItemSelected(int itemId) {
        if(tender.getUserId().equals(Authentication.getUserId())) {
            if (itemId == R.id.deleteMenu) {
                tenderDA.deleteTender(tender, new DACallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean isSuccess) {
                        if (isActivityNotNull()) {
                            if (isSuccess) {
                                activity.finish();
                            } else {
                                Toast.makeText(activity, "Failed to delete tender", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(String message) {
                        if (isActivityNotNull()) {
                            Toast.makeText(activity, "Failed to delete tender", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    }

    private boolean isActivityNotNull(){
        return activity != null;
    }

    public void onCreateOptionMenu(Menu menu) {
        if(tender.getUserId().equals(Authentication.getUserId())){
            activity.getMenuInflater().inflate(R.menu.tender_detail_menu, menu);
        }
    }
}
