package isshukan.com.bukatender.screen.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.BaseScreen;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBidFragment extends Fragment implements BaseScreen{


    public MyBidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_bid, container, false);
    }

    @Override
    public void loadViews() {

    }

    @Override
    public void setDefaultSetting() {

    }

    @Override
    public boolean isControllerNotNull() {
        return false;
    }
}
