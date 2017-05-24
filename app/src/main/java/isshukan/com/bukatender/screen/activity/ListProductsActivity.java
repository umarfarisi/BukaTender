package isshukan.com.bukatender.screen.activity;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.activity.controller.ListProductsController;

public class ListProductsActivity extends BaseActivity{
    private RecyclerView productsRecyclerView;
    private ListProductsController controller;

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_list_products);
        productsRecyclerView = (RecyclerView) findViewById(R.id.productsRecyclerView);
    }

    @Override
    public void setDefaultSetting() {
        controller = new ListProductsController(this);
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

//    public void configureRecyclerView(List<Product> products){
//        adapter = new ProductAdapter()
//    }
}
