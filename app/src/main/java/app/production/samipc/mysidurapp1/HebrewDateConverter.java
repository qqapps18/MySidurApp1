package app.production.samipc.mysidurapp1;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.DateFormatSymbols;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import app.production.samipc.mysidurapp1.HebrewDate.HebrewDate;
import app.production.samipc.mysidurapp1.HebrewDate.HebrewDateException;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.graphics.Color.parseColor;

public class HebrewDateConverter extends AppCompatActivity {

    private RelativeLayout dateCovertLayout;
    private RelativeLayout dateRequestLayout;

    TextView tv;
    TextView tv2;

    NumberPicker dayPicker;
    NumberPicker monthPicker;
    NumberPicker yearPicker;
    HebrewDate selectedDate;
    Switch sconvert;

    String[] monthNames;
    final String[] hmonth = {"Nissan", "Iyar",	"Sivan", "Tamuz", "Av", "Elul", "Tishrei", "Cheshvan", "Kislev", "Teves", "Shvat", "Adar", "Adar II"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hebrew_date_converter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv =  findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        dayPicker = findViewById(R.id.dayPicker);
        monthPicker = findViewById(R.id.monthPicker);
        yearPicker = findViewById(R.id.yearPicker);
        sconvert = findViewById(R.id.sconvert);
        selectedDate = new HebrewDate();

        DateFormatSymbols symbols = new DateFormatSymbols();
        monthNames = symbols.getMonths();

        tv.setTextColor(parseColor("#492E01"));
        tv2.setTextColor(parseColor("#6D14C2"));

        configureDayPicker(false);
        configureMonthPicker(false);
        configureYearPicker(false);

        sconvert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                configureDayPicker(isChecked);
                configureMonthPicker(isChecked);
                configureYearPicker(isChecked);
            }
        });
    }

    private void configureDayPicker(Boolean swValue) {
        if (!swValue) {
            dayPicker.setMinValue(1);
            dayPicker.setMaxValue(selectedDate.getLastDayOfMonth());
            dayPicker.setValue(selectedDate.getDate());
        } else {

            dayPicker.setMinValue(1);
            dayPicker.setMaxValue(selectedDate.getLastDayOfHebrewMonth());
            dayPicker.setValue(selectedDate.getHebrewDate());
        }

        dayPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newDayVal){

                HebrewDate date = new HebrewDate();

                if (!sconvert.isChecked()) {
                    try {
                        date.setDate(monthPicker.getValue() + 1, newDayVal, yearPicker.getValue());
                        date.setDate(monthPicker.getValue() + 1, newDayVal, yearPicker.getValue());

                    } catch (HebrewDateException e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    try {
                        date.setHebrewDate(monthPicker.getValue() + 1, newDayVal, yearPicker.getValue());
                    } catch (HebrewDateException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                performDayPickerChangesForNewDate(date);
            }
        });
    }

    private void updateSelectedDate(HebrewDate newDate) {
        String fh = getString(R.string.fhebrew);
        String gh = getString(R.string.fgregorian);

        selectedDate = newDate;
        tv.setText(fh + "\n" + newDate.getHebrewDateAsString());
        tv2.setText(gh + "\n" + newDate.getDateAsString());
    }

    private void performDayPickerChangesForNewDate(HebrewDate newDate) {
        updateSelectedDate(newDate);
    }

    private void configureMonthPicker(Boolean swValue) {
        if (!swValue) {
            monthPicker.setMinValue(0);
            monthPicker.setMaxValue(monthNames.length-1);
            monthPicker.setDisplayedValues(monthNames);
            monthPicker.setWrapSelectorWheel(true);
            monthPicker.setValue(selectedDate.getMonth() - 1);
        } else {
            monthPicker.setMinValue(0);
            monthPicker.setDisplayedValues(hmonth);

            if (selectedDate.isHebrewLeapYear()) {
                monthPicker.setMaxValue(hmonth.length-1);
            } else {
                monthPicker.setMaxValue(hmonth.length-2);
            }

            monthPicker.setWrapSelectorWheel(true);


            monthPicker.setValue(selectedDate.getHebrewMonth() - 1);
        }

        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newMonthVal){
                HebrewDate date = new HebrewDate();

                if (!sconvert.isChecked()) {
                    try {
                        date.setDate(newMonthVal + 1, dayPicker.getValue(), yearPicker.getValue());
                    } catch (HebrewDateException e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    try {
                        date.setHebrewDate(newMonthVal + 1, dayPicker.getValue(), yearPicker.getValue());
                    } catch (HebrewDateException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                performMonthPickerChangeForNewDate(date);
            }
        });
    }

    private void performMonthPickerChangeForNewDate(HebrewDate newDate) {
        if (!sconvert.isChecked()) {
            dayPicker.setMaxValue(newDate.getLastDayOfMonth());
            try {
                newDate.setDate(newDate.getMonth(), dayPicker.getValue(), newDate.getYear());
            } catch (HebrewDateException e) {
                e.printStackTrace();
            }

        } else {
            dayPicker.setMaxValue(newDate.getLastDayOfHebrewMonth());
            try {
                newDate.setHebrewDate(newDate.getHebrewMonth(), dayPicker.getValue(), newDate.getHebrewYear());
            } catch (HebrewDateException e) {
                e.printStackTrace();
            }
        }

        performDayPickerChangesForNewDate(newDate);
    }

    private void configureYearPicker(Boolean swValue) {
        HebrewDate date = new HebrewDate();

        if (!swValue) {
            yearPicker.setMinValue(date.getYear() - 100);
            yearPicker.setMaxValue(date.getYear() + 100);
            yearPicker.setWrapSelectorWheel(false);
            yearPicker.setValue(selectedDate.getYear());
        } else {
            yearPicker.setMinValue(date.getHebrewYear() - 100);
            yearPicker.setMaxValue(date.getHebrewYear() + 100);
            yearPicker.setWrapSelectorWheel(false);
            yearPicker.setValue(selectedDate.getHebrewYear());
        }

        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newYearVal){
                HebrewDate date = new HebrewDate();

                if (!sconvert.isChecked()) {
                    try {
                        date.setDate(monthPicker.getValue() + 1, dayPicker.getValue(), newYearVal);
                    } catch (HebrewDateException e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    try {
                        date.setHebrewDate(monthPicker.getValue() + 1, dayPicker.getValue(), newYearVal);
                    } catch (HebrewDateException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                performYearPickerChangeForNewDate(date);
            }
        });
    }

    private void performYearPickerChangeForNewDate(HebrewDate newDate) {

        if (!sconvert.isChecked()) {
            monthPicker.setMaxValue(monthNames.length-1);
            try {
                newDate.setMonth(monthPicker.getValue() + 1);
            } catch (HebrewDateException e) {
                e.printStackTrace();
            }
        } else {
            if (newDate.isHebrewLeapYear()) {
                monthPicker.setMaxValue(hmonth.length - 1);
            } else {
                monthPicker.setMaxValue(hmonth.length - 2);
            }

            try {
                newDate.setHebrewMonth(monthPicker.getValue() + 1);
            } catch (HebrewDateException e) {
                e.printStackTrace();
            }
        }

        performMonthPickerChangeForNewDate(newDate);
    }




/*    private DatePickerDialog.OnDateSetListener picDateConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hebrew_date_converter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textPicDate = findViewById(R.id.textPicDate);
        dateToConvertView = findViewById(R.id.dateToConvertView);
        hebrewDateYurzaitView = findViewById(R.id.hebrewDateYurzaitView);
        dateCovertLayout = findViewById(R.id.dateCovertLayout);
        dateRequestLayout = findViewById(R.id.dateRequestLayout);

        dateCovertLayout.setVisibility(View.INVISIBLE);
        dateRequestLayout.setVisibility(View.INVISIBLE);

        textPicDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dateCovertLayout.setVisibility(View.INVISIBLE);
                dateRequestLayout.setVisibility(View.INVISIBLE);

                calendar = Calendar.getInstance();

                daySelect = calendar.get(Calendar.DAY_OF_MONTH);
                monthSelect = calendar.get(Calendar.MONTH);
                yearSelect = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        HebrewDateConverter.this,
                        android.R.style.Theme_DeviceDefault_Light_Panel,
                        picDateConvert,
                        yearSelect, monthSelect, daySelect);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setGravity(200);
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

                dateCovertLayout.setVisibility(View.VISIBLE);
                dateRequestLayout.setVisibility(View.VISIBLE);
            }
        };
    }
*/






}
