package com.example.samipc.mysidurapp1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.samipc.mysidurapp1.HebrewDate.HebrewDate;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HebrewDateConverter extends AppCompatActivity {

    Calendar calendar;
    TextView textPicDate;
    TextView todayDateView;
    TextView hebrewDateTodayView;
    TextView dateToConvertView;
    TextView hebrewDateYurzaitView;
    String dateToConvertString;
    String hebrewDateYurzaitString;
    int yearSelect;
    int monthSelect;
    int daySelect;
    int dia;
    int mes;
    int año;

    DatePickerDialog.OnDateSetListener picDateConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hebrew_date_converter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textPicDate = (TextView) findViewById(R.id.textPicDate);
        dateToConvertView = (TextView) findViewById(R.id.dateToConvertView);
        hebrewDateYurzaitView = (TextView) findViewById(R.id.hebrewDateYurzaitView);

        textPicDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();

                daySelect = calendar.get(Calendar.DAY_OF_MONTH);
                monthSelect = calendar.get(Calendar.MONTH);
                yearSelect = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        HebrewDateConverter.this,
                        android.R.style.Theme_DeviceDefault_Light_Panel,
                        picDateConvert,
                        yearSelect, monthSelect, daySelect);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
                dialog.show();

            }

        });

        picDateConvert = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yearSelect, int monthSelect, int daySelect) {
                monthSelect = monthSelect + 1;

                final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
                //dateToConvertString = dateFormat.format(datePicker);

                dateToConvertView.setText(daySelect + "/" + monthSelect + "/" + yearSelect);


                Date diayurzait = new Date((yearSelect - 1900), (monthSelect - 1), daySelect);
                hebrewDateYurzaitString = dateFormat.format(diayurzait);


                HebrewDate hebrewDateYurzait = new HebrewDate(diayurzait);
                hebrewDateYurzaitView.setText(hebrewDateYurzait.toString());


            }
        };


    }




}
