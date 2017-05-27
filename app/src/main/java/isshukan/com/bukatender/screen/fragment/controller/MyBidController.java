package isshukan.com.bukatender.screen.fragment.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.constant.Constant;
import isshukan.com.bukatender.dataaccess.api.BidDA;
import isshukan.com.bukatender.dataaccess.callback.DACallback;
import isshukan.com.bukatender.model.Bid;
import isshukan.com.bukatender.screen.activity.MylapakActivity;
import isshukan.com.bukatender.screen.fragment.MyBidFragment;
import isshukan.com.bukatender.support.utils.Authentication;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class MyBidController{

    private MyBidFragment fragment;

    private BidDA bidDA;

    private List<Bid> bids;
    private Bid deletedBid;

    public MyBidController(MyBidFragment fragment) {
        this.fragment = fragment;
        bidDA = new BidDA();
    }

    public void loadData() {
        fragment.getProgressBar().setVisibility(View.VISIBLE);
        fragment.getBidRecyclerView().setVisibility(View.GONE);
        bidDA.getUserBid(Authentication.getUserId(), new DACallback<List<Bid>>() {
            @Override
            public void onSuccess(List<Bid> bids) {
                if(isFragmentAndContextNotNull()) {
                    MyBidController.this.bids = bids;
                    fragment.configureRecyclerView(bids);
                    if (bids.isEmpty()) {
                        fragment.getEmptyTextView().setVisibility(View.VISIBLE);
                    } else {
                        fragment.getEmptyTextView().setVisibility(View.GONE);
                        fragment.getBidRecyclerView().setVisibility(View.VISIBLE);
                    }
                    fragment.getProgressBar().setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String message) {
                if(isFragmentAndContextNotNull()) {
                    Toast.makeText(fragment.getContext(), message, Toast.LENGTH_LONG).show();
                    if (bids == null || bids.isEmpty()) {
                        fragment.getEmptyTextView().setVisibility(View.VISIBLE);
                    } else {
                        fragment.getEmptyTextView().setVisibility(View.GONE);
                        fragment.getBidRecyclerView().setVisibility(View.VISIBLE);
                    }
                    fragment.getProgressBar().setVisibility(View.GONE);
                }
            }
        });
    }

    private boolean isFragmentAndContextNotNull(){
        return fragment != null && fragment.getContext() != null;
    }

    public void onBidChooseForLongTime(int position) {
        deletedBid = bids.get(position);
        fragment.getDialog().show();
    }

    public void onClick(int id) {
        if(id == R.id.deleteButton && deletedBid != null){
            bidDA.deleteBid(deletedBid, new DACallback<Boolean>() {
                @Override
                public void onSuccess(Boolean isSuccess) {
                    if(isFragmentAndContextNotNull()){
                        if(isSuccess){
                            bids.remove(deletedBid);
                            fragment.getAdapter().notifyDataSetChanged();
                            if(bids.isEmpty()){
                                fragment.getEmptyTextView().setVisibility(View.VISIBLE);
                            }
                        }else{
                            Toast.makeText(fragment.getContext(),"ERROR: failed to delete bid",Toast.LENGTH_SHORT).show();
                        }
                        fragment.getDialog().dismiss();
                    }
                }

                @Override
                public void onFailure(String message) {
                    if(isFragmentAndContextNotNull()){
                        Toast.makeText(fragment.getContext(),message,Toast.LENGTH_SHORT).show();
                        fragment.getDialog().dismiss();
                    }
                }
            });
        }
    }

    public void onBidChoose(int position) {
        Bid bid = bids.get(position);
        Intent intent = new Intent(fragment.getActivity(), MylapakActivity.class);
        intent.putExtra(Constant.PRODUCT_ID, bid.getProductId());
        intent.putExtra(Constant.USER_ID, bid.getUserBidId());
        intent.putExtra(Constant.PURPOSE, Constant.PURPOSE_SEE_PRODUCT);
        fragment.startActivity(intent);
    }
}
