package isshukan.com.bukatender;

import android.content.Context;

import isshukan.com.bukatender.support.utils.GlobalVariable;

/**
 * @author Muhammad Umar Farisi
 * @created 22/05/2017
 */
public class Application extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        GlobalVariable.APP_CONTEXT = this;
    }
}
