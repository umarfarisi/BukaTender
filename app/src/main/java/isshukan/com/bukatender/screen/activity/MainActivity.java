package isshukan.com.bukatender.screen.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.activity.controller.MainController;
import isshukan.com.bukatender.support.adapter.MainAdapter;

public class MainActivity extends BaseActivity{

    private MainController controller;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MainAdapter adapter;

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    public void setDefaultSetting() {
        controller = new MainController(this);
    }

    public void setUpTabAndViewPager(String[] titles, Fragment[] fragments){
        adapter = new MainAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean isControllerNotNull() {
        return false;
    }
}
