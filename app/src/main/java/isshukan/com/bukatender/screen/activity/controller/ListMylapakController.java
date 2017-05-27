package isshukan.com.bukatender.screen.activity.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.dataaccess.api.MylapakDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Mylapak;
import isshukan.com.bukatender.screen.activity.ListMylapakActivity;
import isshukan.com.bukatender.screen.activity.MylapakActivity;
import isshukan.com.bukatender.support.utils.Authentication;

/**
 * Created by - on 25/05/2017.
 */

public class ListMylapakController {

    private static final int PRODUCT_DETAIL_REQ_CODE = 1;
    private ListMylapakActivity activity;
    private List<Mylapak> mylapaks;

    private MylapakDA mylapakDA;

    public ListMylapakController(ListMylapakActivity activity) {
        this.activity = activity;
        mylapakDA = new MylapakDA();
        fetchMylapak();
    }

    public void fetchMylapak() {

        activity.getProgressBar().setVisibility(View.VISIBLE);
        activity.getMylapakRecyclerView().setVisibility(View.GONE);

        mylapakDA.getAllMylapakUser(Authentication.getUserId(), Authentication.getUserToken(), new DACallback<List<Mylapak>>() {
            @Override
            public void onSuccess(List<Mylapak> mylapaks) {
                ListMylapakController.this.mylapaks = mylapaks;
                activity.configureRecyclerView(mylapaks);
                if (mylapaks.isEmpty()) {
                    activity.getEmptyTextView().setVisibility(View.VISIBLE);
                } else {
                    activity.getEmptyTextView().setVisibility(View.GONE);
                    activity.getMylapakRecyclerView().setVisibility(View.VISIBLE);
                }
                activity.getProgressBar().setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                if (mylapaks == null || mylapaks.isEmpty()) {
                    activity.getEmptyTextView().setVisibility(View.VISIBLE);
                } else {
                    activity.getEmptyTextView().setVisibility(View.GONE);
                    activity.getMylapakRecyclerView().setVisibility(View.VISIBLE);
                }
                activity.getProgressBar().setVisibility(View.GONE);
            }
        });

    }

    public void onMylapakChoose(int position) {
        Intent intent = new Intent();
        intent.setClass(activity, MylapakActivity.class);
        intent.putExtra(Constant.PRODUCT, mylapaks.get(position));
        intent.putExtra(Constant.PURPOSE, Constant.PURPOSE_ADD_BID);
        activity.startActivityForResult(intent, PRODUCT_DETAIL_REQ_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == PRODUCT_DETAIL_REQ_CODE){
                activity.setResult(Activity.RESULT_OK, data);
                activity.finish();
            }
        }
    }
}
