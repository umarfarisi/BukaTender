package isshukan.com.bukatender.screen.activity.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Date;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.constant.ConstantAPI;
import isshukan.com.bukatender.dataaccess.api.TenderDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.ListBidActivity;
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
    private TenderDetailActivity activity;

    private Tender tender;
    private TenderDA tenderDA;

    public TenderDetailController(TenderDetailActivity activity) {
        this.activity = activity;
        tenderDA = new TenderDA();
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
                    //Bid
                }
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EDIT_TENDER_REQ_CODE){
            if(resultCode == Activity.RESULT_OK){
                this.tender = (Tender) data.getSerializableExtra(Constant.TENDER);
                loadData();
            }
        }
    }

    public void onOptionItemSelected(int itemId) {
        if(itemId == R.id.deleteMenu){
            tenderDA.deleteTender(tender, new DACallback<Boolean>() {
                @Override
                public void onSuccess(Boolean isSuccess) {
                    if(isActivityNotNull()) {
                        if (isSuccess) {
                            activity.finish();
                        } else {
                            Toast.makeText(activity, "Failed to delete tender", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(String message) {
                    if(isActivityNotNull()){
                        Toast.makeText(activity, "Failed to delete tender", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private boolean isActivityNotNull(){
        return activity != null;
    }

}
