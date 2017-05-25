package isshukan.com.bukatender.screen.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import isshukan.com.bukatender.R;
import isshukan.com.bukatender.screen.activity.controller.SetTenderController;

public class SetTenderActivity extends BaseActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private TextView titleTextView;
    private TextView startingPriceTextView;
    private TextView shortDescriptionTextView;
    private Button validityPeriodButton;
    private Button imageResourceButton;
    private Button actionButton;

    private DatePickerDialog datePickerDialog;

    private SetTenderController controller;

    @Override
    public void loadViews() {
        setContentView(R.layout.activity_set_tender);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        startingPriceTextView = (TextView) findViewById(R.id.startingPriceTextView);
        shortDescriptionTextView = (TextView) findViewById(R.id.shortDescriptionTextView);
        validityPeriodButton = (Button) findViewById(R.id.validityPeriodButton);
        imageResourceButton = (Button) findViewById(R.id.imageResourceButton);
        actionButton = (Button) findViewById(R.id.actionButton);
    }

    @Override
    public void setDefaultSetting() {
        validityPeriodButton.setOnClickListener(this);
        imageResourceButton.setOnClickListener(this);
        actionButton.setOnClickListener(this);
        controller = new SetTenderController(this);
    }

    public void configureDatePickerDialog(Calendar calendar){
        datePickerDialog = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public TextView getStartingPriceTextView() {
        return startingPriceTextView;
    }

    public TextView getShortDescriptionTextView() {
        return shortDescriptionTextView;
    }

    public Button getActionButton() {
        return actionButton;
    }

    public DatePickerDialog getDatePickerDialog() {
        return datePickerDialog;
    }

    @Override
    public boolean isControllerNotNull() {
        return controller != null;
    }

    @Override
    public void onClick(View v) {
        if(isControllerNotNull()){
            controller.onClick(v.getId());
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(isControllerNotNull()){
            controller.onValidityPeriodChange(year, month, dayOfMonth);
        }
    }
}
