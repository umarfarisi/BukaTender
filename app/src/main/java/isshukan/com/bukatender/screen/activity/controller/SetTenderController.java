package isshukan.com.bukatender.screen.activity.controller;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.dataaccess.api.TenderDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.activity.SetTenderActivity;
import isshukan.com.bukatender.support.utils.Authentication;

/**
 * @author Muhammad Umar Farisi
 * @created 25/05/2017
 */
public class SetTenderController {

    private static final int PICK_IMAGE_REQ_CODE = 1;
    private SetTenderActivity activity;

    private Tender oldTender;
    private Tender newTender;

    private TenderDA tenderDA;

    public SetTenderController(SetTenderActivity activity) {
        this.activity = activity;
        loadIntent();
        loadData();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(newTender.getValidityPeriod()));
        activity.configureDatePickerDialog(calendar);
        tenderDA = new TenderDA();
    }

    private void loadData() {
        if(isEditMode()){
            activity.getTitleTextView().setText(oldTender.getTitle());
            activity.getStartingPriceTextView().setText(String.valueOf(oldTender.getStartingPrice()));
            activity.getShortDescriptionTextView().setText(oldTender.getShortDescription());
            newTender = new Tender(oldTender.getTenderId(), oldTender.getUserId(), oldTender.getTitle(), oldTender.getValidityPeriod(), oldTender.getStartingPrice(), oldTender.getImageResource(), oldTender.getShortDescription(), oldTender.getTag());
            activity.getActionButton().setText("EDIT");
        }else{
            newTender = new Tender(-1, Authentication.getUserId(), "", new Date().getTime(), 0, "", "");
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
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(new Date(newTender.getValidityPeriod()));
                activity.getDatePickerDialog().updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                activity.getDatePickerDialog().show();
                break;
            case R.id.imageResourceButton:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQ_CODE);
                break;
            case R.id.actionButton:
                newTender.setTitle(activity.getTitleTextView().getText().toString());
                newTender.setStartingPrice(Double.valueOf(activity.getStartingPriceTextView().getText().toString()));
                newTender.setShortDescription(activity.getShortDescriptionTextView().getText().toString());
                if(isEditMode()){
                    tenderDA.updateTender(oldTender, newTender, new DACallback<Boolean>() {
                        @Override
                        public void onSuccess(Boolean isSuccess) {
                            if(isSuccess){
                                Intent intentResult = new Intent();
                                intentResult.putExtra(Constant.TENDER, newTender);
                                activity.setResult(Activity.RESULT_OK, intentResult);
                                activity.finish();
                            }else{
                                Toast.makeText(activity, "Failed to save data", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(String message) {
                            Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    tenderDA.createTender(newTender, new DACallback<Boolean>() {
                        @Override
                        public void onSuccess(Boolean isSuccess) {
                            if(isSuccess){
                                activity.finish();
                            }else{
                                Toast.makeText(activity, "Failed to save data", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(String message) {
                            Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
                        }
                    });
                }
                break;
        }
    }

    private boolean isEditMode(){
        //tidak ada oldTender yg dipassing lewat intent yg artinya buat oldTender baru
        return oldTender != null;
    }

    public void onValidityPeriodChange(int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
        newTender.setValidityPeriod(calendar.getTime().getTime());
    }

    public void onImageChange(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE_REQ_CODE){
            if(resultCode == Activity.RESULT_OK && data != null){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
                    //TODO
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
