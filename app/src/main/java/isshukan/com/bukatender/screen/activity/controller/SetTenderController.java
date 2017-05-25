package isshukan.com.bukatender.screen.activity.controller;

import android.content.Intent;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.SetTenderActivity;

/**
 * @author Muhammad Umar Farisi
 * @created 25/05/2017
 */
public class SetTenderController {

    private SetTenderActivity activity;

    private Tender oldTender;
    private Tender newTender;

    public SetTenderController(SetTenderActivity activity) {
        this.activity = activity;
        loadIntent();
        loadData();
    }

    private void loadData() {
        if(isEditMode()){
            activity.getTitleTextView().setText(oldTender.getTitle());
            activity.getStartingPriceTextView().setText(String.valueOf(oldTender.getStartingPrice()));
            activity.getShortDescriptionTextView().setText(oldTender.getShortDescription());
            newTender = new Tender(oldTender.getTenderId(), oldTender.getUserId(), oldTender.getTitle(), oldTender.getValidityPeriod(), oldTender.getStartingPrice(), oldTender.getImageResource(), oldTender.getShortDescription());
            activity.getActionButton().setText("EDIT");
        }else{
            newTender = new Tender(-1, null, null, 0, 0, null, null);
            activity.getActionButton().setText("SAVE");
        }
    }

    private void loadIntent() {
        Intent intent = activity.getIntent();
        oldTender = (Tender) intent.getSerializableExtra(Constant.TENDER);
    }

    public void onClick(int id) {
        switch (id){
            case R.id.validityPeriodButton:
                //TODO
                break;
            case R.id.imageResourceButton:
                //TODO
                break;
            case R.id.actionButton:
                //TODO
                newTender.setTitle(activity.getTitleTextView().getText().toString());
                newTender.setStartingPrice(Double.valueOf(activity.getStartingPriceTextView().getText().toString()));
                newTender.setShortDescription(activity.getShortDescriptionTextView().getText().toString());
                break;
        }
    }

    private boolean isEditMode(){
        //tidak ada oldTender yg dipassing lewat intent yg artinya buat oldTender baru
        return oldTender != null;
    }

}
