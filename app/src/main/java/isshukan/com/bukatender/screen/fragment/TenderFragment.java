package isshukan.com.bukatender.screen.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.model.Tender;
import isshukan.com.bukatender.screen.BaseScreen;
import isshukan.com.bukatender.screen.fragment.controller.TenderController;
import isshukan.com.bukatender.support.adapter.TenderAdapter;
import isshukan.com.bukatender.support.adapter.viewholder.listener.TenderListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TenderFragment extends BaseFragment{

    private View view;
    private RecyclerView tenderRecyclerView;
    private ProgressBar progressBar;
    private TextView emptyTextView;

    private TenderAdapter adapter;
    private TenderController controller;


    private TenderListener listener = new TenderListener() {
        @Override
        public void onTenderChoose(int position) {
            if(isControllerNotNull()){
                controller.onTenderChoose(position);
            }
        }
    };

    public TenderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tender, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isControllerNotNull()){
            controller.loadData();
        }
    }

    @Override
    public void loadViews() {
        tenderRecyclerView = (RecyclerView) view.findViewById(R.id.tenderRecyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        emptyTextView = (TextView) view.findViewById(R.id.emptyTextView);
    }

    @Override
    public void setDefaultSetting() {
        controller = new TenderController(this);
    }

    public void configureRecyclerView(List<Tender> tenders){
        adapter = new TenderAdapter(tenders, getContext(), listener);
        tenderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tenderRecyclerView.setAdapter(adapter);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public TextView getEmptyTextView() {
        return emptyTextView;
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }
}
