package isshukan.com.bukatender.screen.activity.controller;

import android.content.Intent;
import android.support.v4.app.Fragment;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.activity.LoginActivity;
import isshukan.com.bukatender.screen.activity.MainActivity;
import isshukan.com.bukatender.screen.fragment.MyBidFragment;
import isshukan.com.bukatender.screen.fragment.MyTenderFragment;
import isshukan.com.bukatender.screen.fragment.TenderFragment;
import isshukan.com.bukatender.support.utils.Authentication;

/**
 * @author Muhammad Umar Farisi
 * @created 10/05/2017
 */
public class MainController {

    private static final int TOTAL_OF_PAGES = 3;
    private static final int INDEX_OF_MY_TENDER = 0;
    private static final int INDEX_OF_ALL_TENDER = 1;
    private static final int INDEX_OF_MY_BID = 2;

    private MainActivity activity;

    private String[] titels;
    private Fragment[] fragments;

    public MainController(MainActivity activity) {
        this.activity = activity;
        initialization();
    }

    private void initialization() {
        titels = new String[TOTAL_OF_PAGES];
        titels[INDEX_OF_MY_TENDER] = "MY TENDER";
        titels[INDEX_OF_ALL_TENDER] = "ALL TENDER";
        titels[INDEX_OF_MY_BID] = "MY BID";
        fragments = new Fragment[TOTAL_OF_PAGES];
        fragments[INDEX_OF_MY_TENDER] = new MyTenderFragment();
        fragments[INDEX_OF_ALL_TENDER] = new TenderFragment();
        fragments[INDEX_OF_MY_BID] = new MyBidFragment();

        activity.setUpTabAndViewPager(titels, fragments);

    }

    public void onOptionItemSelected(int itemId) {
        if(itemId == R.id.logoutMenu){
            Authentication.removeUserId();
            Authentication.removeUserToken();
            activity.startActivity(new Intent(activity, LoginActivity.class));
            activity.finish();
        }
    }
}
