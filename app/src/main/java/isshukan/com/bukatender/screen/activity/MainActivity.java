package isshukan.com.bukatender.screen.activity;

import isshukan.com.bukatender.screen.activity.controller.MainController;

public class MainActivity extends BaseActivity{

    private MainController controller;

    @Override
    public void loadViews() {

    }

    @Override
    public void setDefaultSetting() {
        controller = new MainController(this);
    }

    @Override
    public boolean isControllerNotNull() {
        return false;
    }
}
