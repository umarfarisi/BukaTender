package isshukan.com.bukatender.screen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import isshukan.com.bukatender.screen.BaseScreen;

/**
 * @author Muhammad Umar Farisi
 * @created 10/05/2017
 */
public abstract class BaseFragment extends Fragment implements BaseScreen {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadViews();
        setDefaultSetting();
    }
}
