package isshukan.com.bukatender.screen.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.BaseScreen;
import isshukan.com.bukatender.screen.fragment.controller.MyTenderController;
import isshukan.com.bukatender.support.adapter.TenderAdapter;
import isshukan.com.bukatender.support.adapter.viewholder.listener.TenderListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTenderFragment extends BaseFragment{

    private RecyclerView tenderRecyclerView;
    private View view;

    private TenderAdapter adapter;

    private MyTenderController controller;

    private TenderListener listener = new TenderListener() {
        @Override
        public void onTenderChoose(int position) {
            if(isControllerNotNull()){
                controller.onTenderChoose(position);
            }
        }
    };

    public MyTenderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_tender, container, false);
        return view;
    }

    @Override
    public void loadViews() {
        tenderRecyclerView = (RecyclerView) view.findViewById(R.id.tenderRecyclerView);
    }

    @Override
    public void setDefaultSetting() {
        controller = new MyTenderController(this);
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

    public void configureRecyclerView(List<Tender> tenders){
        adapter = new TenderAdapter(tenders, getContext(), listener);
        tenderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tenderRecyclerView.setAdapter(adapter);
    }
}
