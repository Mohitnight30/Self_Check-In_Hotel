package com.example.hotelselfcheck_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.Month;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button mDataPic,procedure;
    private NumberPicker numberPicker;
    private TextView date,ac,nonac,price;
    int numDay,typ=600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = findViewById(R.id.textView16);
        ac=findViewById(R.id.textView5);
        nonac=findViewById(R.id.textView15);
        price=findViewById(R.id.textView11);
        ac=findViewById(R.id.textView5);
        nonac=findViewById(R.id.textView15);


        Spinner spino = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this,R.array.roomtype, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(aa);
        spino.setOnItemSelectedListener(this);

        mDataPic = findViewById(R.id.button2);

        Calendar calendar= Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();

        long today=MaterialDatePicker.todayInUtcMilliseconds();
        calendar.setTimeInMillis(today);

        CalendarConstraints.Builder constraintsBuilder =new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());

        MaterialDatePicker.Builder<Pair<Long,Long>> builder= MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select s Date");
        builder.setCalendarConstraints(constraintsBuilder.build());
        final MaterialDatePicker materialDatePicker = builder.build();

        mDataPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "Date_Picker");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long,Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                //get the selected date
                date.setText(materialDatePicker.getHeaderText());
                Long startDate = selection.first;
                Long endDate = selection.second;
                long numDays = endDate - startDate;
                numDay = (int)TimeUnit.MILLISECONDS.toDays(numDays);

            }

        });


        numberPicker= findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                //set in text view
                int rn=newVal;
                int cost=typ*numDay*rn;
                price.setText("Rs. "+cost);

            }
        });


        procedure = findViewById(R.id.button);
        procedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numDay>=1){
                    Intent i = new Intent(getApplicationContext(),information.class);
                    startActivity(i);
                }
                else{
                    Snackbar snackbar= Snackbar.make(view," Please Select Dates ",Snackbar.LENGTH_LONG);
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    snackbar.setAction("OKAY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //for highlight the required part
                        }
                    });
                    snackbar.show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //text is the spinner data
        //String text = adapterView.getItemAtPosition(i).toString();
        if(i==1){
            typ=200;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}